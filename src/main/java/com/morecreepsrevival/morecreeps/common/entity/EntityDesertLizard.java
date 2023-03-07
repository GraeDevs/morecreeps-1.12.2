package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import com.sun.org.apache.xpath.internal.operations.Bool;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDesertLizard extends EntityCreepBase {
    private static final String[] textures = {
            "textures/entity/desertlizard1",
            "textures/entity/desertlizard2",
            "textures/entity/desertlizard3",
            "textures/entity/desertlizard4",
            "textures/entity/desertlizard5"
    };

    public EntityDesertLizard(World worldin) {

        super(worldin);

        setCreepTypeName("Desert Lizard");
        creatureType = EnumCreatureType.MONSTER;

        baseHealth = 15.0f;

        setSize(2f, 0.75f);

        baseSpeed = 0.25d;

        baseAttackDamage = 2.0d;

        experienceValue = 10;

        updateAttributes();
    }

    @Override
    protected void initEntityAI()
    {
        clearAITasks();
        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        nodeProcessor.setCanEnterDoors(false);

        tasks.addTask(1, new EntityAISwimming(this));

        tasks.addTask(2, new EntityAIBreakDoor(this));

        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.8d));

        tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(5, new EntityAILookIdle(this));

        tasks.addTask(6, new AILizardFireballAttack(this));

        tasks.addTask(6, new EntityAIAttackMelee(this, 0.55d, false));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    /*public float getEyeHeight()
    {
        return 0.25F;
    }*/

    public class AILizardFireballAttack extends EntityAIBase {

        public int fireballTime;
        private final EntityDesertLizard parentEntity;

        public AILizardFireballAttack(EntityDesertLizard lizard) {
            this.parentEntity = lizard;
        }

        public boolean shouldExecute() {
            EntityLivingBase entityLivingBase = parentEntity.getAttackTarget();

            return entityLivingBase != null && entityLivingBase.isEntityAlive();
        }

        public void startExecuting()
        {
            fireballTime = 0;
        }

        public void updateTask() {
            --fireballTime;
            EntityLivingBase entityLivingBase = world.getClosestPlayerToEntity(parentEntity, 30D);
            double d = 64d;

            if (entityLivingBase != null && canEntityBeSeen(entityLivingBase)) {

                double d1 = entityLivingBase.getDistanceSq(parentEntity);
                if (d1 < d * d && d1 > 10D) {
                   if(fireballTime <= 0) {
                       double d2 = entityLivingBase.posX - posX;
                       double d3 = (entityLivingBase.getEntityBoundingBox().minY + (double) (entityLivingBase.height / 2.0F)) - (posY + (double) (height / 2.0F));
                       double d4 = (entityLivingBase.posZ - posZ) + 0.5D;
                       renderYawOffset = rotationYaw = (-(float) Math.atan2(d2, d4) * 180F) / (float) Math.PI;
                       EntityDesertLizardFireball fireball = new EntityDesertLizardFireball(world, parentEntity, d2, d3, d4);
                       double d5 = 4D;
                       Vec3d vec3d = parentEntity.getLook(1.0F);
                       fireball.posX = posX + vec3d.x * d5;
                       fireball.posY = posY + (double) (height / 2.0F) + 0.5D;
                       fireball.posZ = posZ + vec3d.z * d5;
                       parentEntity.playSound(CreepsSoundHandler.desertLizardFireball,1.0f, 1.0f);
                       world.spawnEntity(fireball);
                       fireballTime = 80;
                   }
                }
            }
        }
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(10) == 0)
        {
            dropItem(Items.COOKED_PORKCHOP, rand.nextInt(5) + 1);
        }

        if (rand.nextInt(10) == 0)
        {
            dropItem(Items.BONE, rand.nextInt(8) + 1);
        }
    }

    @Override
    protected String[] getAvailableTextures()
    {
        return textures;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.desertLizardHurt;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.desertLizardDeath;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.desertLizard;
    }

}
