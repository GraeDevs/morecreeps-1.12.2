package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelS extends ModelBase {
    private final ModelRenderer Body;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer RightLeg;
    private final ModelRenderer RightEye;
    private final ModelRenderer LeftEye;
    private final ModelRenderer Hat;
    public ModelS() {
        textureWidth = 64;
        textureHeight = 64;

        Body = new ModelRenderer(this);
        Body.setRotationPoint(0.0F, 24.0F, 0.0F);
        Body.cubeList.add(new ModelBox(Body, 44, 40, -12.0F, -33.0F, -1.0F, 5, 5, 4, 0.0F, false));
        Body.cubeList.add(new ModelBox(Body, 44, 40, 7.0F, -23.0F, -1.0F, 5, 5, 4, 0.0F, false));
        Body.cubeList.add(new ModelBox(Body, 0, 0, -12.0F, -38.0F, -1.0F, 24, 5, 4, 0.0F, false));
        Body.cubeList.add(new ModelBox(Body, 0, 9, -12.0F, -28.0F, -1.0F, 24, 5, 4, 0.0F, false));
        Body.cubeList.add(new ModelBox(Body, 0, 18, -12.0F, -18.0F, -1.0F, 24, 5, 4, 0.0F, false));
        Body.cubeList.add(new ModelBox(Body, 52, 0, -2.0F, -41.0F, 0.0F, 4, 31, 2, 0.0F, false));

        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-14.0F, -2.0F, 1.0F);
        RightArm.cubeList.add(new ModelBox(RightArm, 0, 27, -2.0F, 0.0F, -2.0F, 4, 17, 4, 0.0F, false));

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(13.0F, -2.0F, 1.0F);
        LeftArm.cubeList.add(new ModelBox(LeftArm, 0, 27, -1.0F, 0.0F, -2.0F, 4, 17, 4, 0.0F, false));

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setRotationPoint(4.0F, 11.0F, 1.0F);
        LeftLeg.cubeList.add(new ModelBox(LeftLeg, 28, 44, -2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F, false));

        RightLeg = new ModelRenderer(this);
        RightLeg.setRotationPoint(-4.0F, 11.0F, 1.0F);
        RightLeg.cubeList.add(new ModelBox(RightLeg, 28, 44, -2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F, false));

        RightEye = new ModelRenderer(this);
        RightEye.setRotationPoint(-2.0F, -4.0F, -1.0F);
        RightEye.cubeList.add(new ModelBox(RightEye, 28, 27, -2.0F, -2.0F, -1.0F, 3, 3, 1, 0.0F, false));

        LeftEye = new ModelRenderer(this);
        LeftEye.setRotationPoint(2.0F, -4.0F, -1.0F);
        LeftEye.cubeList.add(new ModelBox(LeftEye, 12, 27, -1.0F, -2.0F, -1.0F, 3, 3, 1, 0.0F, false));

        Hat = new ModelRenderer(this);
        Hat.setRotationPoint(0.0F, -15.0F, 1.0F);
        Hat.cubeList.add(new ModelBox(Hat, 7, 56, -3.5F, -1.0F, -3.5F, 7, 1, 7, 0.0F, false));
        Hat.cubeList.add(new ModelBox(Hat, 19, 33, -2.5F, -5.0F, -2.5F, 5, 4, 5, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        Body.render(f5);
        RightArm.render(f5);
        LeftArm.render(f5);
        LeftLeg.render(f5);
        RightLeg.render(f5);
        RightEye.render(f5);
        LeftEye.render(f5);
        Hat.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        LeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount * 0.5f;
        RightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount * 0.5f;
        RightLeg.rotateAngleZ = 0.0f;
        LeftLeg.rotateAngleZ = 0.0f;

        RightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount * 0.5f;
        LeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount * 0.5f;
        RightArm.rotateAngleZ = 0.0f;
        LeftArm.rotateAngleZ = 0.0f;

        RightArm.rotateAngleY = 0.0f;
        LeftArm.rotateAngleY = 0.0f;
    }
}
