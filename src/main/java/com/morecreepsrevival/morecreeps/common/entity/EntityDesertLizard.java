package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
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

    private int fireballStrength = 1;

    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.<Boolean>createKey(EntityGhast.class, DataSerializers.BOOLEAN);
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

        //tasks.addTask(6, new AIFireballAttack(this));

        tasks.addTask(6, new EntityAIAttackMelee(this, 0.55d, false));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @SideOnly(Side.CLIENT)
    public boolean isAttacking()
    {
        return ((Boolean)this.dataManager.get(ATTACKING)).booleanValue();
    }

    public void setAttacking(boolean attacking)
    {
        this.dataManager.set(ATTACKING, Boolean.valueOf(attacking));
    }

    //pulled from Ghast code, probably worst idea ever but worth a shot before constructing my own thing
    /*public class AIFireballAttack extends EntityAIBase
    {
        private final EntityDesertLizard parentEntity;
        public int attackTimer;

        public AIFireballAttack(EntityDesertLizard lizard)
        {
            this.parentEntity = lizard;
        }

        public boolean shouldExecute()
        {
            return this.parentEntity.getAttackTarget() != null;
        }

        public void startExecuting()
        {
            this.attackTimer = 0;
        }

        public void resetTask()
        {
            this.parentEntity.setAttacking(false);
        }

        public void updateTask()
        {
            EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
            double d0 = 64.0D;

            if (entitylivingbase.getDistanceSq(this.parentEntity) < 20.0D && this.parentEntity.canEntityBeSeen(entitylivingbase))
            {
                World world = this.parentEntity.world;
                ++this.attackTimer;

                if (this.attackTimer == 10)
                {
                    world.playEvent((EntityPlayer)null, 1015, new BlockPos(this.parentEntity), 0);
                }

                if (this.attackTimer == 20)
                {
                    double d1 = 4.0D;
                    Vec3d vec3d = this.parentEntity.getLook(1.0F);
                    double d2 = entitylivingbase.posX - (this.parentEntity.posX + vec3d.x * 4.0D);
                    double d3 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (0.5D + this.parentEntity.posY + (double)(this.parentEntity.height / 2.0F));
                    double d4 = entitylivingbase.posZ - (this.parentEntity.posZ + vec3d.z * 4.0D);
                    world.playEvent((EntityPlayer)null, 1016, new BlockPos(this.parentEntity), 0);
                    EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, this.parentEntity, d2, d3, d4);
                    entitylargefireball.explosionPower = this.parentEntity.fireballStrength;
                    entitylargefireball.posX = this.parentEntity.posX + vec3d.x * 4.0D;
                    entitylargefireball.posY = this.parentEntity.posY + (double)(this.parentEntity.height / 2.0F) + 0.5D;
                    entitylargefireball.posZ = this.parentEntity.posZ + vec3d.z * 4.0D;
                    world.spawnEntity(entitylargefireball);
                    this.attackTimer = -40;
                }
            }
            else if (this.attackTimer > 0)
            {
                --this.attackTimer;
            }
        }
    }*/

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
