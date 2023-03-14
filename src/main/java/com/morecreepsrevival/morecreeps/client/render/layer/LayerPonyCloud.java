package com.morecreepsrevival.morecreeps.client.render.layer;

import com.morecreepsrevival.morecreeps.client.models.ModelGooGoat;
import com.morecreepsrevival.morecreeps.client.models.ModelPonyCloud;
import com.morecreepsrevival.morecreeps.client.render.RenderPonyCloud;
import com.morecreepsrevival.morecreeps.common.entity.EntityPonyCloud;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public class LayerPonyCloud implements LayerRenderer<EntityPonyCloud>
{
    private final RenderPonyCloud renderer;

    private final ModelBase model = new ModelPonyCloud();

    public LayerPonyCloud(RenderPonyCloud rendererIn)
    {
        renderer = rendererIn;
    }

    @Override
    public void doRenderLayer(EntityPonyCloud entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (!entity.isInvisible())
        {
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

            GlStateManager.enableNormalize();

            GlStateManager.enableBlend();

            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

            model.setModelAttributes(renderer.getMainModel());

            model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

            GlStateManager.disableBlend();

            GlStateManager.disableNormalize();
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return true;
    }
}
