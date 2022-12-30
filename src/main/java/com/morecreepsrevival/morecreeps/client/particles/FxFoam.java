package com.morecreepsrevival.morecreeps.client.particles;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class FxFoam extends Particle {

    public FxFoam(World world,double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn,double d, double d1,double d2) {
        super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        setSize(0.5F, 0.5F);

        particleRed = 1.0F;
        particleBlue = 1.0F;
        particleGreen = 1.0F;
        particleGravity = 1.8F;
        particleScale *= 1.75F;
        particleTextureJitterX *= 1.5;
        particleTextureJitterY *= 1.5;
        multipleParticleScaleBy(5);
        particleMaxAge *= 2.0;

        motionX += d * 0.33999999463558197d;
        motionY += d1 * 0.33999999463558197d;
        motionZ += d2 * 0.33999999463558197d;
    }

    @Override
    public int getFXLayer() {
        return 2;
    }

    //Unknown what original developer of this override was trying to accomplish. reimplemented code from the original renderParticle class to patch this class up to work.
    //even then it does not work. I am just going to leave this entire block commented out. If someone else can figure this out please be my guest.
    //I reverted the entire block back to how I found it.
    /*@Override
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
    }*/
}
