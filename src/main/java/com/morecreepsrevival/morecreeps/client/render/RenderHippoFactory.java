package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityHippo;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.swing.text.html.parser.Entity;

public class RenderHippoFactory implements IRenderFactory<EntityHippo> {

    @Override
    public Render<? super EntityHippo> createRenderFor(RenderManager renderManager) { return new RenderHippo<>(renderManager); }
}
