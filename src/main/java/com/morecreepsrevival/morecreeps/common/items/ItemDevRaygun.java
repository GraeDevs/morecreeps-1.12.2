package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.entity.EntityDevRay;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemDevRaygun extends CreepsItem
{
    public ItemDevRaygun()
    {
        this("dev_raygun", false);
    }

    public ItemDevRaygun(String itemName, boolean noCreativeTab)
    {
        super(itemName, noCreativeTab);

        setMaxStackSize(1);

        setMaxDamage(500);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.playSound(CreepsSoundHandler.raygunSound, getSoundVolume(), getSoundPitch());

        player.getHeldItem(hand).damageItem(2, player);

        EntityDevRay ray = new EntityDevRay(world, player);

        ray.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 1.6f, 0.0f);

        if (!world.isRemote)
        {
            world.spawnEntity(ray);
        }

        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public boolean isFull3D()
    {
        return true;
    }
}
