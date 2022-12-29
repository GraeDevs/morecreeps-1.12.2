package com.morecreepsrevival.morecreeps.client.particles;

import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.client.renderer.BufferBuilder;

public class FxFoam extends Particle {

    public FxFoam(World world, double d, double d1, double d2, float f) {
        super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
        setSize(0.3F, 0.3F);

        particleRed = 1.0F;
        particleBlue = 1.0F;
        particleGreen = 1.0F;
        particleGravity = 1.55F;
        particleScale *= 1.2F;
    }

    @Override
    public int getFXLayer() {
        return 2;
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        float f6 = ((float)(this.particleTextureIndexX % 16) + super.particleTextureJitterX / 14.0F) / 16.0F;
        float f7 = f6 + 0.01560938F;
        float f8 = ((float)(this.particleTextureIndexY/ 16) + super.particleTextureJitterY / 14.0F) / 16.0F;
        float f9 = f8 + 0.01560938F;
        float f10 = 0.1F * super.particleScale;
        float f11 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)partialTicks - Particle.interpPosX);
        float f12 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)partialTicks - Particle.interpPosY);
        float f13 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)partialTicks - Particle.interpPosZ);
        float f14 = this.getBrightnessForRender(partialTicks);
    }
}
