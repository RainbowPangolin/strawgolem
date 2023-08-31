package com.t2pellet.strawgolem;

import com.t2pellet.strawgolem.events.CropGrowthEvent;
import com.t2pellet.strawgolem.events.CropGrowthHandler;
import com.t2pellet.tlib.TLibForgeMod;
import com.t2pellet.tlib.client.TLibModClient;
import com.t2pellet.tlib.common.TLibMod;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Consumer;


@Mod(Constants.MOD_ID)
@TLibMod.IMod(Constants.MOD_ID)
public class StrawgolemForge extends TLibForgeMod {

    @Override
    protected TLibMod getCommonMod() {
        return StrawgolemCommon.INSTANCE;
    }

    @Override
    protected TLibModClient getClientMod() {
        return StrawgolemClient.INSTANCE;
    }

    @Override
    protected void registerEvents() {
        MinecraftForge.EVENT_BUS.addListener((Consumer<CropGrowthEvent>) event -> {
            if (event.getLevel() instanceof ServerLevel serverLevel) {
                CropGrowthHandler.onCropGrowth(serverLevel, event.getPos());
            }
        });
    }
}