package com.morecreepsrevival.morecreeps.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemAtom extends CreepsItem
{
    public ItemAtom()
    {
        super("atom");

        setMaxStackSize(16);
    }
}
