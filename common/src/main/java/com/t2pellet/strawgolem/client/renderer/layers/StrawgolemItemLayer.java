package com.t2pellet.strawgolem.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.t2pellet.strawgolem.entity.StrawGolem;
import com.t2pellet.strawgolem.entity.capabilities.held_item.HeldItem;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class StrawgolemItemLayer extends GeoLayerRenderer<StrawGolem> {

    private final ItemInHandRenderer itemInHandRenderer;

    public StrawgolemItemLayer(IGeoRenderer<StrawGolem> entityRendererIn, ItemInHandRenderer itemInHandRenderer) {
        super(entityRendererIn);
        this.itemInHandRenderer = itemInHandRenderer;
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, StrawGolem golem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        HeldItem heldItem = golem.getHeldItem();
        if (!golem.getHarvester().isHarvesting() && heldItem.has()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0, 0.6F, -0.32F);
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            this.itemInHandRenderer.renderItem(golem, heldItem.get(), ItemTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.popPose();
        }
    }
}
