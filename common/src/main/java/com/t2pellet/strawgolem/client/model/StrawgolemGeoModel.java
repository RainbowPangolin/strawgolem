package com.t2pellet.strawgolem.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.t2pellet.strawgolem.Constants;
import com.t2pellet.strawgolem.StrawgolemConfig;
import com.t2pellet.strawgolem.entity.StrawGolem;
import com.t2pellet.strawgolem.entity.capabilities.decay.DecayState;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.util.RenderUtils;
import software.bernie.geckolib.model.GeoModel;


public class StrawgolemGeoModel extends GeoModel<StrawGolem> {
    private static final ResourceLocation modelResource = new ResourceLocation(Constants.MOD_ID, "geo/strawgolem.geo.json");
    private static final ResourceLocation animationResource = new ResourceLocation(Constants.MOD_ID, "animations/strawgolem.animation.json");

    // Textures
    private static final ResourceLocation newTextureResource = new ResourceLocation(Constants.MOD_ID, "textures/straw_golem.png");
    private static final ResourceLocation oldTextureResource = new ResourceLocation(Constants.MOD_ID, "textures/straw_golem_old.png");
    private static final ResourceLocation dyingTextureResource = new ResourceLocation(Constants.MOD_ID, "textures/straw_golem_dying.png");

    // Magic numbers
    private static final float ITEM_TRANSLATE_FACTOR = 0.165F;


    @Override
    public ResourceLocation getModelResource(StrawGolem golem) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(StrawGolem golem) {
        if (!StrawgolemConfig.Visual.golemDecayingTexture.get()) return newTextureResource;

        DecayState state = golem.getDecay().getState();
        switch (state) {
            case NEW -> {
                return newTextureResource;
            }
            case OLD -> {
                return oldTextureResource;
            }
            default -> {
                return dyingTextureResource;
            }
        }
    }



    @Override
    public ResourceLocation getAnimationResource(StrawGolem golem) {
        return animationResource;
    }



    @Override
    public void setCustomAnimations(StrawGolem animatable, long instanceId, AnimationState<StrawGolem> customPredicate) {
        super.setCustomAnimations(animatable, instanceId, customPredicate);
        CoreGeoBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (head != null) {
            head.setRotX(extraData.headPitch() * (float) Math.PI / 180);
            head.setRotY(extraData.netHeadYaw() * (float) Math.PI / 180);
        }
    }

    public void translateToHand(PoseStack poseStack) {
        GeoBone arms = (GeoBone) getBone("arms").orElse(null);
        GeoBone upper = (GeoBone) getBone("upper").orElse(null);
        RenderUtils.prepMatrixForBone(poseStack, upper);
        RenderUtils.translateAndRotateMatrixForBone(poseStack, upper);
        RenderUtils.prepMatrixForBone(poseStack, arms);
        RenderUtils.translateAndRotateMatrixForBone(poseStack, arms);
    }
}
