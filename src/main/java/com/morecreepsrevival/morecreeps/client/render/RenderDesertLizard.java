package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelDesertLizard;
import com.morecreepsrevival.morecreeps.client.models.ModelRatMan;
import com.morecreepsrevival.morecreeps.client.models.ModelVHS;
import com.morecreepsrevival.morecreeps.common.entity.EntityDesertLizard;
import com.morecreepsrevival.morecreeps.common.entity.EntityRatMan;
import com.morecreepsrevival.morecreeps.common.entity.EntityVHS;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderDesertLizard<T extends EntityDesertLizard> extends RenderCreep<T>
{
    public RenderDesertLizard(RenderManager renderManager)
    {
        super(renderManager, new ModelDesertLizard(), 0.5f);
    }
}
