package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelPonyCloud;
import com.morecreepsrevival.morecreeps.client.render.layer.LayerBlackSoulEyes;
import com.morecreepsrevival.morecreeps.client.render.layer.LayerPonyCloud;
import com.morecreepsrevival.morecreeps.common.entity.EntityPonyCloud;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntitySlime;

import javax.annotation.Nullable;

public class RenderPonyCloud<T extends EntityPonyCloud> extends RenderCreep<T>
{
    public RenderPonyCloud(RenderManager renderManager)
    {
        super(renderManager, new ModelPonyCloud(), 0f);
        addLayer(new LayerPonyCloud(this));
    }

    @Override
    protected void doScaling(T entity)
    {
        float modelSize = entity.getModelSize();

        GlStateManager.scale(modelSize, modelSize, modelSize);
    }

    @Override
    public void doRender(@Nullable T entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.enableNormalize();

        GlStateManager.enableBlend();

        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);

        //GlStateManager.color(1.0f, 1.0f, 1.0f, 0.9f);

        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        //GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        GlStateManager.disableBlend();

        GlStateManager.disableNormalize();
    }
}
