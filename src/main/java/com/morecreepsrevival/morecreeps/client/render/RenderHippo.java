package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelHippo;
import com.morecreepsrevival.morecreeps.common.entity.EntityHippo;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderHippo<T extends EntityHippo> extends RenderCreep<T>
{
    public RenderHippo(RenderManager renderManager) { super(renderManager, new ModelHippo(), 0.5f); }
}
