package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemPeeBuck extends CreepsItem {
    public ItemPeeBuck()
    {
        super("pee_bucket");

        setMaxStackSize(1);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.playSound(CreepsSoundHandler.ponyDrink, 1.0f, 1.0f);
        player.getHeldItem(hand).shrink(1);

        return super.onItemRightClick(world, player, hand);
    }
}
