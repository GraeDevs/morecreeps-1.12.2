package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelRockMonster;
import com.morecreepsrevival.morecreeps.common.entity.EntityRockMonster;
import com.morecreepsrevival.morecreeps.common.entity.EntitySchlump;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

public class RenderRockMonsterFactory implements IRenderFactory<EntityRockMonster>
{


    @Override
    public Render<? super EntityRockMonster> createRenderFor(RenderManager renderManager)
    {
        return new RenderRockMonster<>(renderManager);
    }
}
