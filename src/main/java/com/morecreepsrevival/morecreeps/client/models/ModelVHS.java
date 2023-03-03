package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVHS extends ModelBase {

    public ModelRenderer body;
    public ModelRenderer rightLeg;
    public ModelRenderer rightArm;
    public ModelRenderer leftLeg;
    public ModelRenderer leftArm;
    public boolean heldItemLeft;
    public boolean heldItemRight;
    public boolean isSneak;

    public ModelVHS()
    {
        this(0.0F);
    }

    public ModelVHS(float f)
    {
        this(f, 0.0F);
    }

    public ModelVHS(float f, float f1) {
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
        body.render(f5);
        rightLeg.render(f5);
        leftLeg.render(f5);
        rightArm.render(f5);
        leftArm.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
