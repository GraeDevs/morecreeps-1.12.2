package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;

public class ModelVHS extends ModelBase {

    public ModelRenderer body;
    public ModelRenderer rightLeg;
    public ModelRenderer rightArm;
    public ModelRenderer leftLeg;
    public ModelRenderer leftArm;
    public boolean heldItemLeft;
    public boolean heldItemRight;
    public boolean isSneak;
    public ModelVHS() {
        textureWidth = 96;
        textureHeight = 96;

        heldItemLeft = false;
        heldItemRight = false;
        isSneak = false;

        body = new ModelRenderer(this, 0, 0);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.cubeList.add(new ModelBox(body, 0, 0, -14.0F, -19.0F, -9.0F, 28, 4, 18, 0.0F, false));

        rightLeg = new ModelRenderer(this);
        rightLeg.setRotationPoint(-5.0F, 9.0F, 0.0F);
        rightLeg.cubeList.add(new ModelBox(rightLeg, 0, 0, -1.0F, 0.0F, -1.0F, 2, 15, 2, 0.0F, false));

        leftLeg = new ModelRenderer(this);
        leftLeg.setRotationPoint(5.0F, 9.0F, 0.0F);
        leftLeg.cubeList.add(new ModelBox(leftLeg, 8, 0, -1.0F, 0.0F, -1.0F, 2, 15, 2, 0.0F, false));

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(-14.0F, 7.0F, 0.0F);
        rightArm.cubeList.add(new ModelBox(rightArm, 8, 22, -2.0F, -1.0F, -1.0F, 2, 14, 2, 0.0F, false));

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(14.0F, 7.0F, 0.0F);
        leftArm.cubeList.add(new ModelBox(leftArm, 0, 22, 0.0F, -1.0F, -1.0F, 2, 14, 2, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
        rightLeg.render(f5);
        leftLeg.render(f5);
        rightArm.render(f5);
        leftArm.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount * 0.5f;
        rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount * 0.5f;
        rightLeg.rotateAngleZ = 0.0f;
        leftLeg.rotateAngleZ = 0.0f;

        rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount * 0.5f;
        leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount * 0.5f;
        rightArm.rotateAngleZ = 0.0f;
        leftArm.rotateAngleZ = 0.0f;

        rightArm.rotateAngleY = 0.0f;
        leftArm.rotateAngleY = 0.0f;

        if (swingProgress > -9990f) {
            float f6 = swingProgress;
        }
    }
}
