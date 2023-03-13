package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.items.ItemVHSTape;
import com.morecreepsrevival.morecreeps.common.networking.CreepsPacketHandler;
import com.morecreepsrevival.morecreeps.common.networking.message.MessageOpenGuiTamableEntityName;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class EntityVHS extends EntityCreepBase implements IEntityCanChangeSize {

    private UUID angerTargetUUID;
    public int angerLevel;

    public EntityVHS(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Walking VHS");
        creatureType = EnumCreatureType.MONSTER;

        baseSpeed = 0.35d;
        baseHealth = 15f;
        angerLevel = 0;

        setSize(1.55f, 1.25f);

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

        tasks.addTask(6, new EntityAIAttackMelee(this, 0.75d, false));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

    }

    @Override
    protected SoundEvent getTamedSound()
    {
        return CreepsSoundHandler.guineaPigLevelUpSound;
    }
    @Override
    public void setRevengeTarget(@Nullable EntityLivingBase livingBase)
    {
        super.setRevengeTarget(livingBase);

        if (livingBase != null)
        {
            this.angerTargetUUID = livingBase.getUniqueID();
        }
    }

    @Override
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
                        EntityVHS vhs = (EntityVHS)entity1;
                        vhs.getAngry(entity);
                    }
                }

                getAngry(entity);
            }
        }

        return super.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if(angry()) {
            --angerLevel;
        }
    }
    private void getAngry(Entity entity)
    {
        setAttackTarget((EntityLivingBase) entity);
        angerLevel += 100 + rand.nextInt(100);

    }
    public boolean angry() { return angerLevel > 0; }
    @Override
    public int getRevengeTimer() { return angerLevel; }
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (hand == EnumHand.OFF_HAND) {
            return super.processInteract(player, hand);
        }

        ItemStack itemStack = player.getHeldItem(hand);
        if(!itemStack.isEmpty()) {
            Item item = itemStack.getItem();

            if(item == CreepsItemHandler.vhsTape) {
                this.setAttackTarget(null);
                playSound(CreepsSoundHandler.vhsInsert, getSoundVolume(), getSoundPitch());
                itemStack.shrink(1);

                for (int j = 0; j < 10; j++)
                {
                    world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, rand.nextGaussian() * 0.02D, rand.nextGaussian() * 0.02D, rand.nextGaussian() * 0.02D);
                }
            }
        }

        return super.processInteract(player, hand);
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(10) == 1)
        {
            dropItem(CreepsItemHandler.vhsTape, rand.nextInt(1) + 1);
        }
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/vhs.png");
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.vhsHurt;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.vhsDeath;
    }

    @Override
    public float maxShrink() { return 0.3f; }

    @Override
    public float getShrinkRayAmount() { return 0.2f; }

    @Override
    public void onShrink(EntityShrink source) {

    }

    @Override
    public float maxGrowth() {
        return 4.0f;
    }

    @Override
    public float getGrowRayAmount()
    {
        return 0.2F;
    }

    @Override
    public void onGrow(EntityGrow source) {

    }
}
