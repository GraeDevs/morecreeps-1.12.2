package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityVHS;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderVHSFactory implements IRenderFactory<EntityVHS>
{
    public Render<? super EntityVHS> createRenderFor(RenderManager renderManager)
    {
        return new RenderVHS<>(renderManager);
    }
}
