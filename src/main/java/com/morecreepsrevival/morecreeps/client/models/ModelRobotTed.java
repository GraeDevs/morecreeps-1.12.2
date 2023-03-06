package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.Rotation;
import net.minecraft.client.particle.ParticleBreaking;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class ModelRobotTed extends ModelBase
{
    public ModelRenderer tedhead;
    public ModelRenderer body;
    public ModelRenderer body2;
    public ModelRenderer legL;
    public ModelRenderer legR;
    public ModelRenderer arm1L;
    public ModelRenderer arm1R;
    public ModelRenderer arm2L;
    public ModelRenderer arm2R;
    public ModelRenderer hornL;
    public ModelRenderer hornR;
    public ModelRenderer eyeL;
    public ModelRenderer eyeR;
    public ModelRenderer visor;
    long bouncePeriod = 100000, sugarPeriod = 25;
    long lastMS;
    float bounceDelta = 0f, sugarDelta = 0f;
    float lastOffsetModification = 0f;

    scala.util.Random sugarRandom = new scala.util.Random();

    public boolean heldItemLeft;
    public boolean heldItemRight;
    public boolean isSneak;

    ModelRenderer[] models;

    public ModelRobotTed()
    {
        this(0.0f);
    }

    public ModelRobotTed(float f)
    {
        this(f, 0.0f);
    }

    public ModelRobotTed(float f, float f1)
    {

        float f2 = 0.0F;
        tedhead = new ModelRenderer(this, 0, 0);
        tedhead.addBox(-2.5F, -6F, -3F, 5, 6, 6, f2);
        tedhead.setRotationPoint(0.0F, 7F, 0.0F);
        body = new ModelRenderer(this, 26, 2);
        body.addBox(-5F, -10F, -4F, 10, 10, 8, f2);
        body.setRotationPoint(0.0F, 17F, 0.0F);
        body2 = new ModelRenderer(this, 36, 24);
        body2.addBox(-4F, 0.0F, -3F, 8, 2, 6, f2);
        body2.setRotationPoint(0.0F, 17F, 0.0F);
        legL = new ModelRenderer(this, 22, 22);
        legL.addBox(-1.5F, 0.0F, -1.5F, 3, 2, 3, f2);
        legL.setRotationPoint(2.0F, 19F, 0.0F);
        legR = new ModelRenderer(this, 22, 22);
        legR.addBox(-1.5F, 0.0F, -1.5F, 3, 2, 3, f2);
        legR.setRotationPoint(-2F, 19F, 0.0F);
        arm1L = new ModelRenderer(this, 0, 12);
        arm1L.addBox(0.0F, 0.0F, -1F, 2, 5, 2, f2);
        arm1L.setRotationPoint(5F, 7F, 0.0F);
        arm1L.rotateAngleZ = -0.17453F;
        arm1R = new ModelRenderer(this, 0, 12);
        arm1R.addBox(-2F, 0.0F, -1F, 2, 5, 2, f2);
        arm1R.setRotationPoint(-5F, 7F, 0.0F);
        arm1R.rotateAngleZ = 0.17453F;
        arm2L = new ModelRenderer(this, 0, 24);
        arm2L.addBox(1.0F, 4.5F, 1.0F, 2, 5, 2, f2);
        arm2L.setRotationPoint(5F, 7F, 0.0F);
        arm2L.rotateAngleX = 0.08727F;
        arm2R = new ModelRenderer(this, 0, 24);
        arm2R.addBox(-3F, 4.5F, 1.0F, 2, 5, 2, f2);
        arm2R.setRotationPoint(-5F, 7F, 0.0F);
        arm2R.rotateAngleX = 0.08727F;
        hornL = new ModelRenderer(this, 8, 12);
        hornL.addBox(0.0F, -10F, -0.5F, 1, 4, 1, f2);
        hornL.setRotationPoint(0.0F, 7F, 0.0F);
        hornL.rotateAngleZ = 0.2618F;
        hornR = new ModelRenderer(this, 8, 12);
        hornR.addBox(-1F, -10F, -0.5F, 1, 4, 1, f2);
        hornR.setRotationPoint(0.0F, 7F, 0.0F);
        hornR.rotateAngleZ = -0.2618F;
        eyeL = new ModelRenderer(this, 8, 24);
        eyeL.addBox(0.5F, -4.5F, -4.5F, 1, 1, 1, f2);
        eyeL.setRotationPoint(0.0F, 7F, 0.0F);
        eyeR = new ModelRenderer(this, 8, 24);
        eyeR.addBox(-1.5F, -4.5F, -4.5F, 1, 1, 1, f2);
        eyeR.setRotationPoint(0.0F, 7F, 0.0F);
        visor = new ModelRenderer(this, 9, 20);
        visor.addBox(-3F, -5F, -3.8F, 6, 2, 1, f2);
        visor.setRotationPoint(0.0F, 7F, 0.0F);

        models = new ModelRenderer[]
        {
            tedhead,
            body,
            body2,
            legL,
            legR,
            arm1L,
            arm1R,
            arm2L,
            arm2R,
            hornL,
            hornR,
            eyeL,
            eyeR,
            visor
        };

        lastMS = System.currentTimeMillis();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        float bounceAmount = 0.1f;

        long currentMS = System.currentTimeMillis();

        long difference = currentMS - lastMS;

        bounceDelta += difference / (double) bouncePeriod;
        sugarDelta += difference / (double) sugarPeriod;

        lastMS = currentMS;

        bounceDelta -= (int) bounceDelta;
        boolean shouldNotSugarSpawn = sugarDelta < 1f;
        sugarDelta -= (int) sugarDelta;

        float sindelta = MathHelper.sin(bounceDelta * 180f);

        float sProgress = sindelta * bounceAmount;

        setBounce(sProgress);

        boolean wichLeg = true;

        for(int i = 0; i < 4; ++i)
        {
            spawnSugar(shouldNotSugarSpawn, entity, sProgress, f3, wichLeg);

            wichLeg = !wichLeg;
        }

        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        tedhead.render(f5);
        body.render(f5);
        body2.render(f5);
        legL.render(f5);
        legR.render(f5);
        arm1L.render(f5);
        arm1R.render(f5);
        arm2L.render(f5);
        arm2R.render(f5);
        hornL.render(f5);
        hornR.render(f5);
        eyeL.render(f5);
        eyeR.render(f5);
        visor.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        tedhead.rotateAngleY = f3 / (180F / (float)Math.PI);
        tedhead.rotateAngleX = f4 / (180F / (float)Math.PI);
        hornR.rotateAngleY = f3 / (180F / (float)Math.PI);
        hornR.rotateAngleX = f4 / (180F / (float)Math.PI);
        hornL.rotateAngleY = f3 / (180F / (float)Math.PI);
        hornL.rotateAngleX = f4 / (180F / (float)Math.PI);
        visor.rotateAngleY = f3 / (180F / (float)Math.PI);
        visor.rotateAngleX = f4 / (180F / (float)Math.PI);
        eyeL.rotateAngleY = f3 / (180F / (float)Math.PI);
        eyeL.rotateAngleX = f4 / (180F / (float)Math.PI);
        eyeR.rotateAngleY = f3 / (180F / (float)Math.PI);
        eyeR.rotateAngleX = f4 / (180F / (float)Math.PI);
        arm1R.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 3F * f1 * 0.5F - 0.5F;
        arm1L.rotateAngleX = MathHelper.cos(f * 0.6662F) * 3F * f1 * 0.5F - 0.5F;
        arm2R.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 3F * f1 * 0.5F - 0.95F;
        arm2L.rotateAngleX = MathHelper.cos(f * 0.6662F) * 3F * f1 * 0.5F - 0.95F;
    }
    @SideOnly(Side.CLIENT)
    private void setBounce(float bounce)
    {
        if(Minecraft.getMinecraft().isGamePaused()) return;

        for (ModelRenderer current : models) {
            current.offsetY += bounce - lastOffsetModification;
        }

        lastOffsetModification = bounce;
    }
    @SideOnly(Side.CLIENT)
    private void spawnSugar(boolean shouldNot, Entity entity, float ychange, float entityYaw, boolean rightLeg)
    {
        if(Minecraft.getMinecraft().isGamePaused()) return;

        if(shouldNot) return;

        double xMov = (sugarRandom.nextInt(8) - 4 + 1) * 0.06;
        double zMov = (sugarRandom.nextInt(8) - 4 + 1) * 0.06;
        double xVariation = (sugarRandom.nextInt(200) - 100) * 0.005;
        double zVariation = (sugarRandom.nextInt(200) - 100) * 0.005;

        //float yaw = entity.rotationYaw + 1.5707963268f * (rightLeg ? 1f : -1f);
        //double legAngleX = MathHelper.sin(yaw) * 0.75;a
        //double legAngleZ = MathHelper.cos(yaw) * 0.75;



        double legMiddleX = entity.posX + xVariation+ (legR.offsetX + (legR.offsetX - legL.offsetX) * 0.5d);
        double legMiddleZ = entity.posZ + zVariation + (legR.offsetZ + (legR.offsetZ - legL.offsetZ) * 0.5d);
        double legY = entity.posY - ychange;

        ParticleBreaking.Factory maker = new ParticleBreaking.Factory();

        Particle sugarParticle = maker.createParticle(-1, entity.world, legMiddleX, legY, legMiddleZ, xMov, 0.5f, zMov, Item.getIdFromItem(Items.SUGAR));

        //sugarParticle.multipleParticleScaleBy(1f);

        Minecraft.getMinecraft().effectRenderer.addEffect(sugarParticle);
    }
}
