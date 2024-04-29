package com.t2pellet.strawgolem.entity.animations;

import com.t2pellet.strawgolem.entity.StrawGolem;
import software.bernie.geckolib.core.object.PlayState;

public class StrawgolemMovementController extends StrawgolemAnimationController {

    public static final String NAME = "move_controller";

    private static final AnimationStateHandler<StrawGolem> PREDICATE = event -> {
        StrawGolem golem = event.getAnimatable();

        String nextAnimation;
        if (golem.getHarvester().isHarvesting()) return PlayState.STOP;
        else if (golem.isRunning()) nextAnimation = "legs_run";
        else if (golem.isMoving()) nextAnimation = "legs_walk";
        else nextAnimation = "legs_idle";

        StrawgolemAnimationController controller = (StrawgolemAnimationController) event.getController();
        controller.setAnimation(nextAnimation);
        return PlayState.CONTINUE;
    };

    public StrawgolemMovementController(StrawGolem animatable) {
        super(animatable, NAME, PREDICATE);
    }

}
