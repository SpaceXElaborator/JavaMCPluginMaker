package com.terturl.PluginMaker.Listeners.Player;

import java.util.HashMap;
import java.util.Map;

import com.terturl.PluginMaker.Listeners.Listener;

public class PlayerJoinListener extends Listener {

	public PlayerJoinListener(String s) {
		super(s + "JoinGame", "./listeners/PlayerJoinListener.ftlh");
	}

	@Override
	public Map<String, Object> getMappings() {
		return new HashMap<>();
	}
	
}