package com.morecreepsrevival.morecreeps.common.entity;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nullable;

public class EntityExtinguisherSmoke extends EntityThrowable
{
    private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, new Predicate<Entity>()
    {
        public boolean apply(@Nullable Entity p_apply_1_)
        {
            assert p_apply_1_ != null;
            return p_apply_1_.canBeCollidedWith();
        }
    });
    private int xTile;
    private int yTile;
    private int zTile;
    protected double initialVelocity;
    private Entity user;
    private int groundTicks;
    public int DECAY;
    private double damage;

    public EntityExtinguisherSmoke(World world) {
        super(world);
        initialVelocity = 0.5D;
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.damage = 5.0;
    }
    public EntityExtinguisherSmoke(World world, Entity entity)
    {
        this(world);
        DECAY = 35;
        setSize(0.25f, 0.25f);
        user = entity;
        isImmuneToFire = true;
        setRotation(entity.rotationYaw, 0.0f);
        double d = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180F);
        double d1 = (-MathHelper.sin(entity.rotationPitch * (float)Math.PI) / 90F);
        double d2 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180F);
        motionX = 0.49999999999999996D * d * (double)MathHelper.cos((entity.rotationPitch / 180F) * (float)Math.PI);
        motionY = -0.60000000000000004D * (double)MathHelper.sin((entity.rotationPitch / 180F) * (float)Math.PI);
        motionZ = 0.49999999999999996D * d2 * (double)MathHelper.cos((entity.rotationPitch / 180F) * (float)Math.PI);
        setPosition(entity.posX + d * 0.40000000000000004D, entity.posY + 1.5d + d1 * 0.40000000000000004D, entity.posZ + d2 * 0.40000000000000004D);
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        rotationYaw = entity.rotationYaw;
        rotationPitch = entity.rotationPitch;
    }

private void extinguish_behaviour(ArrayList<Entity> entities) {
    for (Entity entity : entities) {
            entity.extinguish();
    }
}
private void extinguish_blocks(int xtile,int ytile, int ztile,int radius) {
    //The code below is an algorithm for searching through a radius of blocks. the three for loops are where the functionality lie
    //yeah, 3 for loops :/.
    int killChance = ThreadLocalRandom.current().nextInt(0, 100 + 1);
    int lX = xtile - radius;
    int lY = ytile - radius;
    int lZ = ztile - radius;
    int hX = xtile + radius;
    int hY = ytile + radius;
    int hZ = ztile + radius;
    for (int cX = lX; cX <= hX; cX++) {
        for (int cY = lY; cY <= hY; cY++) {
            for (int cZ = lZ; cZ <= hZ; cZ++) {
                int obsidianChance = ThreadLocalRandom.current().nextInt(0, 6900 + 1);
                int iceChance = ThreadLocalRandom.current().nextInt(0, 4200 + 1);

                BlockPos pos = new BlockPos(cX, cY, cZ);
                IBlockState bstate = this.world.getBlockState(pos);
                Block block = bstate.getBlock();
                //Below are the checkers for checking if the block is on fire or lava
                if ((block.getLocalizedName().equals(Blocks.FIRE.getLocalizedName()))&& (killChance < 50)) {
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                    killChance++;
                }
                else if ((block.getLocalizedName().equals(Blocks.FIRE.getLocalizedName())) && (killChance >= 50)) {
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                    this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, (0.05F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.02F) * 0.5F);
                    setDead();
                    return;
                }
                //if you are a winner, you will get an obsidian block
                if ((block.getLocalizedName().equals(Blocks.LAVA.getLocalizedName())) && (125 <= obsidianChance && 420 >= obsidianChance )){
                    world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
                    MoreCreepsAndWeirdos.proxy.foame(this);
                    this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.75F, (0.5F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
                    setDead();
                    return;
                }
                else if ((block.getLocalizedName().equals(Blocks.LAVA.getLocalizedName())) && ((10 > obsidianChance || 6490 < obsidianChance ))) {
                    world.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
                    MoreCreepsAndWeirdos.proxy.foame(this);
                    this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, (2.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
                    setDead();
                    return;
                }
                else if ((block.getLocalizedName().equals(Blocks.LAVA.getLocalizedName())) && ((20 > obsidianChance || 6380 < obsidianChance ))) {
                    world.setBlockState(pos, Blocks.STONE.getDefaultState());
                    MoreCreepsAndWeirdos.proxy.foame(this);
                    this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, (3.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
                    setDead();
                    return;
                }
                if ((block.getLocalizedName().equals(Blocks.WATER.getLocalizedName())) && (iceChance == 69)){
                    world.setBlockState(pos, Blocks.ICE.getDefaultState());
                    MoreCreepsAndWeirdos.proxy.foame(this);
                    this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_GLASS_PLACE, SoundCategory.BLOCKS, 1.0F, (10.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
                    setDead();
                    return;

                }
            }
        }
    }
}
    @Override
    public void onUpdate() {
        super.onUpdate();
        this.xTile = MathHelper.floor(this.posX);
        this.yTile = MathHelper.floor(this.posY);
        this.zTile = MathHelper.floor(this.posZ);
        prevPosX = posX/1.5;
        prevPosY = posY/1.5;
        prevPosZ = posZ/1.5;
        move(MoverType.SELF, motionX, motionY, motionZ);

        MoreCreepsAndWeirdos.proxy.foame(this);
        Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec3d = new Vec3d(this.posX + 10, this.posY + 10, this.posZ + 10);
        ArrayList<Entity> foundEnts = this.findEntitiesOnPath(vec3d1, vec3d);
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d1, vec3d, false, true, false);
        if (foundEnts != null) {
            extinguish_behaviour(foundEnts);
        }

        if (onGround) {
                if (groundTicks > DECAY) {
                    setDead();
                    this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.BLOCKS, 0.5F, (0.025F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.02F) * 0.01F);
                }
                groundTicks++;
                motionX /=2.0;
                motionZ /=2.0;

            }



            if (raytraceresult != null)
        {
            vec3d = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
        }

        ArrayList<Entity> entitiesInPath = this.findEntitiesOnPath(vec3d1, vec3d);
    if (entitiesInPath != null){

        for (Entity entityInPath : entitiesInPath) {
            if (entityInPath != null && entityInPath != user) {
                raytraceresult = new RayTraceResult(entityInPath);
            }

            if (raytraceresult != null && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onHit(raytraceresult);
            }

        }
        extinguish_blocks(this.xTile,this.yTile,this.zTile,2);
}

        }
    @Nullable
    protected ArrayList<Entity> findEntitiesOnPath(Vec3d start, Vec3d end)
    {
        ArrayList<Entity> entities = new ArrayList<>();


        for (Entity entity1 : this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(this.motionX * 10, this.motionY * 10, this.motionZ * 10).grow(1.0D), ARROW_TARGETS))
        {
            if (!onGround)
            {
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.60000001192092896D);
                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);

                if (raytraceresult != null)
                {
                    entities.add(entity1);
                }
            }
        }
        return entities;
    }
    protected void onHit(RayTraceResult raytraceResultIn) {
        DamageSource damagesource;
        Entity entity = raytraceResultIn.entityHit;
        float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
        float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        int i = MathHelper.ceil((double)f * this.damage);

        if (entity != null) {
            if(entity instanceof EntityLivingBase)
                entity.addVelocity(this.motionX * 0.3 * 0.6000000238418579D / (double)f1, 0.15, this.motionZ * 0.3 * 0.6000000238418579D / (double)f1);
            entity.extinguish();

            if (entity.isImmuneToFire()) {

                damagesource = DamageSource.causeThrownDamage(this, this.user);
                if (entity.attackEntityFrom(damagesource, (float) i)) {
                    if (entity instanceof EntityLivingBase) {
                        EntityLivingBase entitylivingbase = (EntityLivingBase) entity;
                        if (entity.isBurning() || entitylivingbase.isInLava()){
                            int armorFactor = entitylivingbase.getTotalArmorValue();
                            this.damage = (armorFactor/2.0) + (this.damage * 2.0);
                            this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_HOSTILE_SPLASH, SoundCategory.BLOCKS, 1.75F, (0.001F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.02F) * 0.002F);
                        }
                        if (entitylivingbase.isInWater()){
                            this.damage = ((entitylivingbase.getMaxHealth()/4.0) + (this.damage * 2.5));
                            this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_BOBBER_SPLASH, SoundCategory.BLOCKS, 1.75F, (0.001F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.02F) * 0.002F);
                        }

                        else{
                            this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1.75F, (0.001F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.02F) * 0.002F);
                        }

                        if (this.user != null && entitylivingbase != this.user && entitylivingbase instanceof EntityPlayer && this.user instanceof EntityPlayerMP) {
                            ((EntityPlayerMP) this.user).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
                        }
                    }
                }

            }
        }
    }
    @Override
    protected void onImpact(@Nullable RayTraceResult result)
    {
    }
}