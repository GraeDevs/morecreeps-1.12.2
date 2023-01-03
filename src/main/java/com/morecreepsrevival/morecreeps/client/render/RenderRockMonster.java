package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelRockMonster;
import com.morecreepsrevival.morecreeps.client.models.ModelSchlump;
import com.morecreepsrevival.morecreeps.common.entity.EntityRockMonster;
import com.morecreepsrevival.morecreeps.common.entity.EntitySchlump;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public class RenderRockMonster<T extends EntityRockMonster> extends RenderCreep<T>
{
    public RenderRockMonster(RenderManager renderManager)
    {
        super(renderManager, new ModelRockMonster(), 2f);
        shadowSize = 1.5f;
    }

    @Override
    protected void doScaling(T entity)
    {
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
    }

}
