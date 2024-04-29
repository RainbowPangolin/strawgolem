package com.t2pellet.strawgolem.entity.animations;

import com.t2pellet.strawgolem.StrawgolemConfig;
import com.t2pellet.strawgolem.entity.StrawGolem;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;


public class StrawgolemHarvestController extends StrawgolemAnimationController {

    private static PlayState predicate(AnimationState<StrawGolem> event) {
        boolean isHarvesting = event.getAnimatable().getHarvester().isHarvesting();
        if (isHarvesting) {
            // Appropriate animation for regular crop or gourd crop
            RawAnimation anim = null;
            if (event.getAnimatable().getHarvester().isHarvestingBlock()) {
                if (StrawgolemConfig.Visual.showHarvestBlockAnimation.get()) anim = RawAnimation.begin().thenLoop("harvest_block");
                else event.getAnimatable().getHarvester().completeHarvest(); // skip harvesting animation if disabled
            } else {
                if (StrawgolemConfig.Visual.showHarvestItemAnimation.get()) anim = RawAnimation.begin().thenLoop("harvest_item");
                else event.getAnimatable().getHarvester().completeHarvest(); // skip harvesting animation if disabled
            }
            event.getController().setAnimation(anim);
        } // TODO?? else event.getController().clearAnimationCache();
        return isHarvesting ? PlayState.CONTINUE : PlayState.STOP;
    }

    public StrawgolemHarvestController(StrawGolem animatable) {
        super(animatable, "harvest", StrawgolemHarvestController::predicate);
        setCustomInstructionKeyframeHandler(event -> {
            if (event.getKeyframeData().getInstructions().equals("completeHarvest")) {
                animatable.getHarvester().completeHarvest();
            }
        });
    }
}
