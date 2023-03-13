package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHippo extends ModelBase {

    public ModelRenderer headHippo;
    public ModelRenderer snout;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg4;
    public ModelRenderer leg3;
    public ModelRenderer noseR;
    public ModelRenderer noseL;
    public ModelRenderer earR;
    public ModelRenderer earL;
    public ModelRenderer toothL;
    public ModelRenderer toothR;
    public ModelRenderer eyeL;
    public ModelRenderer eyeR;
    public ModelRenderer mouth;
    public float tailwag;
    public int taildirection;

    public ModelHippo() {
        this(0.0F);
    }

    public ModelHippo(float f) {
        this(f, 0.0F);
    }

    public ModelHippo(float f, float f1) {
        this.taildirection = 1;
        float f2 = 0.0F;
        this.headHippo = new ModelRenderer(this, 16, 20);
        this.headHippo.addBox(-3.0F, -3.0F, -5.0F, 6, 7, 5, f2);
        this.headHippo.setRotationPoint(1.0F, 13.0F, -7.0F);
        this.headHippo.rotateAngleY = 0.0F;
        this.snout = new ModelRenderer(this, 38, 22);
        this.snout.addBox(-4.0F, -1.0F, -10.0F, 8, 5, 5, f2);
        this.snout.setRotationPoint(1.0F, 13.0F, -7.0F);
        this.body = new ModelRenderer(this, 12, 0);
        this.body.addBox(-5.0F, -4.0F, -8.0F, 10, 8, 16, 1.35F);
        this.body.setRotationPoint(1.0F, 14.0F, 2.0F);
        this.leg1 = new ModelRenderer(this, 0, 23);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, f2);
        this.leg1.setRotationPoint(-2.0F, 19.0F, -4.0F);
        this.leg2 = new ModelRenderer(this, 0, 23);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, f2);
        this.leg2.setRotationPoint(4.0F, 19.0F, -4.0F);
        this.leg4 = new ModelRenderer(this, 0, 23);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, f2);
        this.leg4.setRotationPoint(4.0F, 19.0F, 8.0F);
        this.leg3 = new ModelRenderer(this, 0, 23);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, f2);
        this.leg3.setRotationPoint(-2.0F, 19.0F, 8.0F);
        this.noseR = new ModelRenderer(this, 8, 0);
        this.noseR.addBox(2.0F, -1.7F, -9.0F, 1, 1, 1, f2);
        this.noseR.setRotationPoint(1.0F, 13.0F, -7.0F);
        this.noseL = new ModelRenderer(this, 4, 0);
        this.noseL.addBox(-3.0F, -1.7F, -9.0F, 1, 1, 1, f2);
        this.noseL.setRotationPoint(1.0F, 13.0F, -7.0F);
        this.earR = new ModelRenderer(this, 0, 6);
        this.earR.addBox(2.0F, -5.0F, -3.0F, 1, 2, 2, f2);
        this.earR.setRotationPoint(1.0F, 13.0F, -7.0F);
        this.earL = new ModelRenderer(this, 0, 2);
        this.earL.addBox(-3.0F, -5.0F, -3.0F, 1, 2, 2, f2);
        this.earL.setRotationPoint(1.0F, 13.0F, -7.0F);
        this.toothL = new ModelRenderer(this, 0, 16);
        this.toothL.addBox(-3.0F, 4.0F, -10.0F, 1, 2, 1, f2);
        this.toothL.setRotationPoint(1.0F, 13.0F, -7.0F);
        this.toothR = new ModelRenderer(this, 0, 16);
        this.toothR.addBox(2.0F, 4.0F, -10.0F, 1, 2, 1, f2);
        this.toothR.setRotationPoint(1.0F, 13.0F, -7.0F);
        this.eyeL = new ModelRenderer(this, 0, 0);
        this.eyeL.addBox(-3.5F, -1.7F, -4.0F, 1, 1, 1, f2);
        this.eyeL.setRotationPoint(1.0F, 13.0F, -7.0F);
        this.eyeR = new ModelRenderer(this, 0, 0);
        this.eyeR.addBox(2.5F, -1.7F, -4.0F, 1, 1, 1, f2);
        this.eyeR.setRotationPoint(1.0F, 13.0F, -7.0F);
        this.mouth = new ModelRenderer(this, 8, 0);
        this.mouth.addBox(-2.5F, 1.0F, -10.0F, 5, 1, 5, f2);
        this.mouth.setRotationPoint(1.0F, 13.0F, -7.0F);
        this.mouth.rotateAngleX = 0.45203F;
        this.mouth.rotateAngleY = 0.0F;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.headHippo.render(f5);
        this.snout.render(f5);
        this.body.render(f5);
        this.leg1.render(f5);
        this.leg2.render(f5);
        this.leg4.render(f5);
        this.leg3.render(f5);
        this.noseR.render(f5);
        this.noseL.render(f5);
        this.earR.render(f5);
        this.earL.render(f5);
        this.toothL.render(f5);
        this.toothR.render(f5);
        this.eyeL.render(f5);
        this.eyeR.render(f5);
        this.mouth.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.headHippo.rotateAngleY = f3 / 57.29578F;
        this.headHippo.rotateAngleX = f4 / 57.29578F;
        this.snout.rotateAngleY = this.noseR.rotateAngleY = this.noseL.rotateAngleY = this.eyeR.rotateAngleY = this.eyeL.rotateAngleY = this.toothL.rotateAngleY = this.toothR.rotateAngleY = this.earL.rotateAngleY = this.earR.rotateAngleY = this.headHippo.rotateAngleY;
        this.snout.rotateAngleX = this.noseR.rotateAngleX = this.noseL.rotateAngleX = this.eyeR.rotateAngleX = this.eyeL.rotateAngleX = this.toothL.rotateAngleX = this.toothR.rotateAngleX = this.earL.rotateAngleX = this.earR.rotateAngleX = this.headHippo.rotateAngleX;
        if (this.taildirection > 0) {
            this.tailwag += 2.0E-4F;
            if (this.tailwag > 0.067F) {
                this.taildirection *= -1;
            }
        } else {
            this.tailwag -= 2.0E-4F;
            if ((double)this.tailwag < -0.067) {
                this.taildirection *= -1;
            }
        }

        this.mouth.rotateAngleX = this.headHippo.rotateAngleX + 0.45203F + this.tailwag;
        this.mouth.rotateAngleY = this.headHippo.rotateAngleY;
        this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
}
