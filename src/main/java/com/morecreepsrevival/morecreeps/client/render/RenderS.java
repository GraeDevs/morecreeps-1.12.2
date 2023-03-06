package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelG;
import com.morecreepsrevival.morecreeps.client.models.ModelS;
import com.morecreepsrevival.morecreeps.common.entity.EntityG;
import com.morecreepsrevival.morecreeps.common.entity.EntityS;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderS<T extends EntityS> extends RenderCreep<T>
{
    public RenderS(RenderManager renderManager)
    {
        super(renderManager, new ModelS(), 0.8f);
    }
}