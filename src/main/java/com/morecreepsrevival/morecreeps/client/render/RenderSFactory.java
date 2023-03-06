package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityG;
import com.morecreepsrevival.morecreeps.common.entity.EntityS;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderSFactory implements IRenderFactory<EntityS>
{
    @Override
    public Render<? super EntityS> createRenderFor(RenderManager renderManager)
    {
        return new RenderS<>(renderManager);
    }
}
