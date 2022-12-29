package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class EntityInvisibleMan extends EntityCreepBase {

    private int angerLevel;
    private static final ItemStack defaultHeldItem;
    private UUID angerTargetUUID;

    public EntityInvisibleMan(World world) {
        super(world);
        setCreepName("Invisible Man");

        creatureType = EnumCreatureType.MONSTER;

        baseHealth = (float)rand.nextInt(40) + 40.0f;

        baseSpeed = 0.3d;

        this.angerLevel = 0;

        super.setTexture("textures/entity/invisibleman.png");

        updateAttributes();

    }

    @Override
    protected void entityInit()
    {
        super.entityInit();


    }

    @Override
    protected void initEntityAI()
    {
        clearAITasks();

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        nodeProcessor.setCanEnterDoors(true);

        tasks.addTask(0, new EntityAISwimming(this));

        this.tasks.addTask(1, new AIAttackEntity());

        tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, true));

        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(5, new EntityAILookIdle(this));

    }

    protected void attackEntity(Entity entity, float f) {
        if (onGround) {
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            float f1 = MathHelper.sqrt(d * d + d1 * d1);
            motionX = (d / (double)f1) * 0.2D * 0.800000011920929D + motionX * 0.20000000298023224D;
            motionZ = (d1 / (double)f1) * 0.2D * 0.800000011920929D + motionZ * 0.20000000298023224D;
            motionY = 0.20000000596246448D;
        }
    }

    public void setRevengeTarget(@Nullable EntityLivingBase livingBase)
    {
        super.setRevengeTarget(livingBase);

        if (livingBase != null)
        {
            this.angerTargetUUID = livingBase.getUniqueID();
        }
    }

    public class AIAttackEntity extends EntityAIBase {

        public EntityInvisibleMan InviM = EntityInvisibleMan.this;
        public int attackTime;
        public AIAttackEntity() {}

        @Override
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.InviM.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive() && angerLevel > 0;
        }

        public void updateTask()
        {
            if(angerLevel > 0) {
                --attackTime;
                EntityLivingBase entitylivingbase = this.InviM.getAttackTarget();
                double d0 = this.InviM.getDistanceSq(entitylivingbase);

                if (d0 < 4.0D)
                {
                    if (this.attackTime <= 0)
                    {
                        this.attackTime = 10;
                        entitylivingbase.motionX = motionX * 3D;
                        entitylivingbase.motionY = rand.nextFloat() * 2.533F;
                        entitylivingbase.motionZ = motionZ * 3D;
                        this.InviM.attackEntityAsMob(entitylivingbase);// or entitylivingbase.attackEntityFrom blablabla...
                    }

                    this.InviM.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
                }
                else if (d0 < 256.0D)
                {
                    // ATTACK ENTITY JUST CALLED HERE :D
                    InviM.attackEntity(entitylivingbase, (float)d0);
                    this.InviM.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
                }
                else
                {
                    this.InviM.getNavigator().clearPath();
                    this.InviM.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 0.5D);
                }
            }
        }
    }

    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
        Entity entity = damagesource.getTrueSource();
        if(entity != null)
        {
            if (entity instanceof EntityPlayer)
            {
                List list = world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(32D, 32D, 32D));

                for (int j = 0; j < list.size(); j++)
                {
                    Entity entity1 = (Entity)list.get(j);

                    if (entity1 instanceof EntityInvisibleMan)
                    {
                        EntityInvisibleMan entityinvisibleman = (EntityInvisibleMan)entity1;
                        entityinvisibleman.becomeAngryAt(entity);
                    }
                }

                becomeAngryAt(entity);
            }
        }

        return super.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    private void becomeAngryAt(Entity entity) {
        this.setAttackTarget((EntityLivingBase)entity);
        angerLevel = 40 + rand.nextInt(40);
        //this.setTexture("textures/entity/invisiblemanmad.png");
    }

    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STICK));
    }

    public void onUpdate() {
        super.onUpdate();
        if(angerLevel == 0) {
            this.setTexture("textures/entity/invisibleman.png");
        }
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if(isAngry()) {
            --angerLevel;
        }
    }


    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }

    static {
        defaultHeldItem = new ItemStack(Items.STICK, 1);
    }

    public boolean isAngry()
    {
        return angerLevel > 0;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return this.angerLevel == 0 ? CreepsSoundHandler.invisibleManSound : CreepsSoundHandler.invisibleManAngry;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.invisibleManHurt;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.invisibleManDeath;
    }
}
