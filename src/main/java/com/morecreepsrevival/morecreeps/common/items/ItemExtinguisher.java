package com.morecreepsrevival.morecreeps.common.items;


import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import com.morecreepsrevival.morecreeps.common.entity.EntityExtinguisherSmoke;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemExtinguisher extends CreepsItem {
    public ItemExtinguisher() {
        super("extinguisher");
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        player.getHeldItem(hand).damageItem(1, player);
        player.playSound(CreepsSoundHandler.extinguisherSound, this.getSoundVolume(), this.getSoundPitch());
        MoreCreepsAndWeirdos.proxy.foam(player);
        EntityExtinguisherSmoke smoke = new EntityExtinguisherSmoke(world, player);
        world.spawnEntity(smoke);
        return super.onItemRightClick(world, player, hand);
    }
    public boolean isFull3D() {
        return true;
    }
}