package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityInvisibleMan;
import com.morecreepsrevival.morecreeps.common.entity.EntityThief;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderInvisibleManFactory implements IRenderFactory<EntityInvisibleMan>
{
    @Override
    public Render<? super EntityInvisibleMan> createRenderFor(RenderManager renderManager)
    {
        return new RenderInvisibleMan<>(renderManager);
    }
}

