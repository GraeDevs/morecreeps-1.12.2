package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityDesertLizard;
import com.morecreepsrevival.morecreeps.common.entity.EntityVHS;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderDesertLizardFactory implements IRenderFactory<EntityDesertLizard>
{
    public Render<? super EntityDesertLizard> createRenderFor(RenderManager renderManager)
    {
        return new RenderDesertLizard<>(renderManager);
    }
}
