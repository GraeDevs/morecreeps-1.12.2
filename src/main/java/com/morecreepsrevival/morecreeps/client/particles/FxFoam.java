package com.morecreepsrevival.morecreeps.client.particles;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;


public class FxFoam extends Particle {
    private int bounceCount;


    public FxFoam(World world,double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn,double d, double d1,double d2) {
        super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        setSize(0.5F, 0.5F);

        particleRed = 1.0F;
        particleBlue = 1.0F;
        particleGreen = 1.0F;
        particleGravity = 1.8F;
        particleTextureJitterX *= 1.5;
        particleTextureJitterY *= 1.5;
        particleMaxAge *= 3.0;

        motionX += d * 0.33999999463558197d;
        motionY += d1 * 0.33999999463558197d;
        motionZ += d2 * 0.33999999463558197d;
        setParticleTextureIndex(4);
        bounceCount = 0;
    }

    //Unknown what original developer of this override was trying to accomplish. reimplemented code from the original renderParticle class to patch this class up to work.
    //even then it does not work. I am just going to leave this entire block commented out. If someone else can figure this out please be my guest.
    //I reverted the entire block back to how I found it.

    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        float f = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge * 32.0F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }

    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setExpired();
        }
        if (onGround){
            if (bounceCount < 3){
                this.motionY += 0.1D;
                bounceCount +=1;
            }
        }
        if (bounceCount > 2){
            this.motionY = 0.004D;
        }
        else {
            this.motionY -= 0.04D;
        }
        this.move(this.motionX, this.motionY, this.motionZ);
        this.particleScale += 0.25F;
        this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);

        if (this.posY == this.prevPosY)
        {
            this.motionX /= 2.0D;
            this.motionZ /= 2.0D;
        }
    }
}
