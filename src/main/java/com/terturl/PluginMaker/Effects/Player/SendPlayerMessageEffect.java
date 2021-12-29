package com.terturl.PluginMaker.Effects.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.terturl.PluginMaker.Effects.PluginEffect;

public class SendPlayerMessageEffect extends PluginEffect {

	private String msg;
	
	public SendPlayerMessageEffect(String message) {
		msg = message;
		this.requiresPlayer = true;
	}
	
	public String getTemplateFile() {
		return "sendPlayerMessage.ftlh";
	}
	
	public String getMessage() {
		return msg;
	}

	public Map<String, Object> getTempMappings() {
		Map<String, Object> temp = new HashMap<>();
		temp.put("Message", msg);
		return temp;
	}

	@Override
	public List<String> getImports() {
		return new ArrayList<>();
	}
	
}