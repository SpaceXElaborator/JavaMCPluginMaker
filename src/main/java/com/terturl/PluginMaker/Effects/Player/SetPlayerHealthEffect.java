package com.terturl.PluginMaker.Effects.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.terturl.PluginMaker.Effects.PluginEffect;

public class SetPlayerHealthEffect extends PluginEffect {

	private String amount;
	private String type;
	
	private boolean canKill;
	private boolean canMax;
	
	public SetPlayerHealthEffect(Double Amount, String Type, boolean CanKill, boolean CanMax) {
		amount = String.valueOf(Amount);
		type = Type;
		canKill = CanKill;
		canMax = CanMax;
		this.requiresPlayer = true;
	}
	
	@Override
	public String getTemplateFile() {
		return "./setPlayerHealthAmount.ftlh";
	}

	@Override
	public Map<String, Object> getTempMappings() {
		Map<String, Object> temp = new HashMap<>();
		
		temp.put("HealthAmount", amount);
		temp.put("HealthType", type);
		temp.put("CanMax", canMax);
		temp.put("CanKill", canKill);
		
		return temp;
	}

	@Override
	public List<String> getImports() {
		return new ArrayList<>();
	}
	
}