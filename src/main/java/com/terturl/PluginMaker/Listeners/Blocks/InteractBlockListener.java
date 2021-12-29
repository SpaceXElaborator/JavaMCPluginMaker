package com.terturl.PluginMaker.Listeners.Blocks;

import java.util.HashMap;
import java.util.Map;

import com.terturl.PluginMaker.Listeners.Listener;
import com.terturl.PluginMaker.Utils.PlayerInteraction;

public class InteractBlockListener extends Listener {
	
	private String action;
	private String block;
	
	public InteractBlockListener(PlayerInteraction a, String b) {
		super(a.getFriendlyName() + b, "./listeners/ClickBlockListener.ftlh");
		action = a.getJavaName();
		block = b;
	}

	@Override
	public Map<String, Object> getMappings() {
		Map<String, Object> listenerAddons = new HashMap<>();
		
		listenerAddons.put("ListenerAction", action);
		listenerAddons.put("ListenerBlock", block);
		
		return listenerAddons;
	}
	
}