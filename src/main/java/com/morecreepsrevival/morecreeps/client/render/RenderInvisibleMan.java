package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityInvisibleMan;
import com.morecreepsrevival.morecreeps.common.entity.EntityLawyerFromHell;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;

public class RenderInvisibleMan<T extends EntityInvisibleMan> extends RenderCreep<T>{

    public RenderInvisibleMan(RenderManager renderManager)
    {
        super(renderManager, new ModelBiped(), 0.5f);

    }
}
