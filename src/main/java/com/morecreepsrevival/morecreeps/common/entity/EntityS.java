package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityS extends EntityCreepBase implements IMob {

    public EntityS(World worldin) {
        super(worldin);

        setCreepTypeName("Money Man S");
        creatureType = EnumCreatureType.MONSTER;

        setSize(width * 4f, height * 2.5f);

        setModelSize(2.0f);

        baseHealth = (float)rand.nextInt(40) + 40.0f;

        baseSpeed = 0.3d;

        baseAttackDamage = 2.0d;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/s.png");
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected void initEntityAI()
    {
        clearAITasks();

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        nodeProcessor.setCanEnterDoors(true);

        tasks.addTask(1, new EntityAISwimming(this));

        tasks.addTask(2, new EntityAIBreakDoor(this));

        tasks.addTask(3, new EntityAIAttackMelee(this, 1.0d, true));

        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float i) {
        Entity entity = damageSource.getTrueSource();
        if(entity != null) {
            if(entity instanceof EntityPlayer) {
                if(!world.isRemote && !this.isChild()) {
                    dropItem(CreepsItemHandler.money, rand.nextInt(3) + 1);
                }
            }
        }
        return super.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(10) == 0)
        {
            dropItem(CreepsItemHandler.money, rand.nextInt(10) + 1);
        }
        if (rand.nextInt(10) == 0)
        {
            dropItem(Items.GOLD_INGOT, rand.nextInt(3) + 1);
        }

    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.sAmbient;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.sHurt;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.sDeath;
    }

}
