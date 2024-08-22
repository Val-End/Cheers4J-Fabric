package akai.cheers;

import akai.cheers.twitch.TwitchHandler;
import akai.cheers.twitch.TwitchListener;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cheers4J implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("twitch-test");

	@Override
	public void onInitialize() {
		LOGGER.info("Cheers4J Initialized!!!");
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> CheersCommand.register(dispatcher));
		ServerLifecycleEvents.SERVER_STARTING.register(listener -> TwitchHandler.connect());
		ServerLifecycleEvents.SERVER_STARTED.register(listener -> TwitchListener.setServer(listener.getCommandSource().getServer()));
		ServerLifecycleEvents.SERVER_STOPPING.register(listener -> TwitchHandler.disconnect());
	}
}