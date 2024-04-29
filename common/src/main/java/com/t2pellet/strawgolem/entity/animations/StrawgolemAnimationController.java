package com.t2pellet.strawgolem.entity.animations;

import com.t2pellet.strawgolem.entity.StrawGolem;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;


public class StrawgolemAnimationController extends AnimationController<StrawGolem> {

    public StrawgolemAnimationController(StrawGolem animatable, String name, AnimationStateHandler<StrawGolem> animationPredicate) {
        super(animatable, name, 4, animationPredicate);
    }

    protected void setAnimation(@NotNull String animation) {
        setAnimation(RawAnimation.begin().thenLoop(animation));
    }

//    protected void setAnimation(@NotNull String animation, ILoopType.EDefaultLoopTypes loopType) {
//        Animation current = getCurrentAnimation();
//        boolean isNewAnimation = current == null || !current.name.equals(animation);
//        if (!animation.isEmpty() && isNewAnimation) {
//            setAnimation(RawAnimation.begin().thenLoop(animation));
//        }
//    }
}
