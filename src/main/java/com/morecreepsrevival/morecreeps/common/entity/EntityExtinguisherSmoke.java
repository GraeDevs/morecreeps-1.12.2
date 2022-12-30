package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityExtinguisherSmoke extends EntityThrowable
{
    private int xTile;
    private int yTile;
    private int zTile;
    protected double initialVelocity;

    public EntityExtinguisherSmoke(World world) {
        super(world);
        initialVelocity = 0.5D;
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
    }
    public EntityExtinguisherSmoke(World world, Entity entity)
    {
        this(world);
        setSize(0.0f, 0.0f);
        isImmuneToFire = true;
        double theta = (entity.rotationPitch) * (float)Math.PI / 180.0f;
        double phi = entity.rotationYaw * (float)Math.PI / 180.0f;
        double d = -MathHelper.sin((float)phi) * MathHelper.cos((float)theta) ;
        double d1 = MathHelper.sin((float)theta);
        double d2 = MathHelper.cos((float)phi) * MathHelper.cos((float)theta);
        double motion[] = {entity.motionX, entity.motionY, entity.motionZ};
        Vec3d vec3 = entity.getLookVec();
        vec3.scale(0.5);

        setPosition(entity.posX + motion[0] + vec3.x,  entity.posY + motion[1] + vec3.y + 2.0, entity.posZ + motion[2] + vec3.z);

        rotationYaw = entity.rotationYaw;
        rotationPitch = entity.rotationPitch;

        motionX = 0.59999999999999998d * d;

        motionY = -0.69999999999999996d * d1;

        motionZ = 0.5999999999999999d * d2;
    }



    @Override
    public void onUpdate() {
        super.onUpdate();
        this.xTile = MathHelper.floor(this.posX);
        this.yTile = MathHelper.floor(this.posY);
        this.zTile = MathHelper.floor(this.posZ);

        BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
        IBlockState iblockstate = this.world.getBlockState(blockpos);
        MoreCreepsAndWeirdos.proxy.foame(this);

        // I am almost certain there is a better way to optimize this ugly block with separate methods
        if (!onGround) {
            for (int i = 0; i < 3; i++) {
                BlockPos blockpos1 = new BlockPos(this.xTile + i, this.yTile + 1, this.zTile);
                IBlockState tempblockstate = this.world.getBlockState(blockpos1);
                Block block1 = tempblockstate.getBlock();
                block1.fillWithRain(this.world, this.getPosition());

                BlockPos blockpos2 = new BlockPos(this.xTile, this.yTile + 1, this.zTile + i);
                tempblockstate = this.world.getBlockState(blockpos2);
                Block block2 = tempblockstate.getBlock();
                block2.fillWithRain(this.world, this.getPosition());

                BlockPos blockpos3 = new BlockPos(this.xTile + i, this.yTile + i, this.zTile + i);
                tempblockstate = this.world.getBlockState(blockpos3);
                Block block3 = tempblockstate.getBlock();
                block3.fillWithRain(this.world, this.getPosition());

                BlockPos blockpos4 = new BlockPos(this.xTile + i, this.yTile + i, this.zTile);
                tempblockstate = this.world.getBlockState(blockpos4);
                Block block4 = tempblockstate.getBlock();
                block4.fillWithRain(this.world, this.getPosition());

                BlockPos blockpos5 = new BlockPos(this.xTile, this.yTile + i, this.zTile + i);
                tempblockstate = this.world.getBlockState(blockpos5);
                Block block5 = tempblockstate.getBlock();
                block5.fillWithRain(this.world, this.getPosition());

                BlockPos blockpos6 = new BlockPos(this.xTile + i, this.yTile + i, this.zTile + i);
                tempblockstate = this.world.getBlockState(blockpos6);
                Block block6 = tempblockstate.getBlock();
                block6.fillWithRain(this.world, this.getPosition());

                if (block1.getLocalizedName().equals(Blocks.FIRE.getLocalizedName())) {
                    world.setBlockState(blockpos1, Blocks.AIR.getDefaultState());
                }
                if (block2.getLocalizedName().equals(Blocks.FIRE.getLocalizedName())) {
                    world.setBlockState(blockpos2, Blocks.AIR.getDefaultState());
                }
                if (block3.getLocalizedName().equals(Blocks.FIRE.getLocalizedName())) {
                    world.setBlockState(blockpos3, Blocks.AIR.getDefaultState());
                }
                if (block4.getLocalizedName().equals(Blocks.FIRE.getLocalizedName())) {
                    world.setBlockState(blockpos4, Blocks.AIR.getDefaultState());
                }
                if (block5.getLocalizedName().equals(Blocks.FIRE.getLocalizedName())) {
                    world.setBlockState(blockpos5, Blocks.AIR.getDefaultState());
                }
                if (block6.getLocalizedName().equals(Blocks.FIRE.getLocalizedName())) {
                    world.setBlockState(blockpos6, Blocks.AIR.getDefaultState());
                }
                if (block1.getLocalizedName().equals(Blocks.LAVA.getLocalizedName())) {
                    world.setBlockState(blockpos1, Blocks.OBSIDIAN.getDefaultState());
                    MoreCreepsAndWeirdos.proxy.foame(this);
                    setDead();
                }
                if (block2.getLocalizedName().equals(Blocks.LAVA.getLocalizedName())) {
                    world.setBlockState(blockpos2, Blocks.OBSIDIAN.getDefaultState());
                    MoreCreepsAndWeirdos.proxy.foame(this);
                    setDead();
                }
                if (block3.getLocalizedName().equals(Blocks.LAVA.getLocalizedName())) {
                    world.setBlockState(blockpos3, Blocks.OBSIDIAN.getDefaultState());
                    MoreCreepsAndWeirdos.proxy.foame(this);
                    setDead();
                }
                if (block4.getLocalizedName().equals(Blocks.LAVA.getLocalizedName())) {
                    world.setBlockState(blockpos4, Blocks.OBSIDIAN.getDefaultState());
                    MoreCreepsAndWeirdos.proxy.foame(this);
                    setDead();
                }
                if (block5.getLocalizedName().equals(Blocks.LAVA.getLocalizedName())) {
                    world.setBlockState(blockpos5, Blocks.OBSIDIAN.getDefaultState());
                    MoreCreepsAndWeirdos.proxy.foame(this);
                    setDead();
                }
                if (block6.getLocalizedName().equals(Blocks.LAVA.getLocalizedName())) {
                    world.setBlockState(blockpos6, Blocks.OBSIDIAN.getDefaultState());
                    MoreCreepsAndWeirdos.proxy.foame(this);
                    setDead();
                }
            }

        }
    }
    @Override
    protected void onImpact(@Nullable RayTraceResult result)
    {
    }
}