package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelRatMan;
import com.morecreepsrevival.morecreeps.client.models.ModelVHS;
import com.morecreepsrevival.morecreeps.common.entity.EntityRatMan;
import com.morecreepsrevival.morecreeps.common.entity.EntityVHS;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderVHS<T extends EntityVHS> extends RenderCreep<T>
{
    public RenderVHS(RenderManager renderManager)
    {
        super(renderManager, new ModelVHS(), 0.5f);
    }
}
