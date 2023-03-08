package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityVHS extends EntityCreepBase implements IEntityCanChangeSize {

    public EntityVHS(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Walking VHS");
        creatureType = EnumCreatureType.MONSTER;

        baseSpeed = 0.35d;
        baseHealth = 15f;

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

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(10) == 0)
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
