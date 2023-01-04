package com.morecreepsrevival.morecreeps.common.items;


import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import com.morecreepsrevival.morecreeps.common.entity.EntityExtinguisherSmoke;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
public class ItemExtinguisher extends CreepsItem {
    private boolean usedInAir;

    public ItemExtinguisher() {
        super("extinguisher");
        this.setMaxStackSize(1);
        this.setMaxDamage(250);
    }
public void generateLift(EntityPlayer player, float gravity, float lift){

    float armorFactor = player.getTotalArmorValue();
    if (armorFactor == 0.0f){
        armorFactor = 1.0f;
    }
    lift = lift - (lift * (armorFactor/100));
    float magY = (((player.rotationPitch * 0.5F) / 90.0F) * lift) / gravity;
    float magX = -Math.abs(((lift*lift)/(0.05F+MathHelper.abs(magY)))*(MathHelper.sin(((player.cameraPitch) * 3.1415927F)/180.0f)) / (gravity));
    player.moveRelative(0.0f,magY*2.0F,magX,1.0f);



}

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {


        player.getHeldItem(hand).damageItem(1, player);
        player.playSound(CreepsSoundHandler.extinguisherSound, this.getSoundVolume(), this.getSoundPitch());
        MoreCreepsAndWeirdos.proxy.foam(player);
        EntityExtinguisherSmoke smoke = new EntityExtinguisherSmoke(world, player);
        world.spawnEntity(smoke);
        if (world.isRemote){
            generateLift(player, 10.0f,3.0f);
        }
        if (player.isAirBorne){
            usedInAir = true;
        }
        return super.onItemRightClick(world, player, hand);
    }
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!(entityIn instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) entityIn;
        if (usedInAir) {
            player.fallDistance = -15.0f;
            if (player.onGround)
            {
                usedInAir = false;
            }
        }
    }
    public boolean isFull3D() {
        return true;
    }
}