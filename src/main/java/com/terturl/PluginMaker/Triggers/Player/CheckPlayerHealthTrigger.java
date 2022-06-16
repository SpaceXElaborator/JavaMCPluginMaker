package com.terturl.PluginMaker.Triggers.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.terturl.PluginMaker.Triggers.PluginTrigger;

public class CheckPlayerHealthTrigger extends PluginTrigger {

	private String triggerCon;
	private String amount;
	
	public CheckPlayerHealthTrigger(String triggerCondition, Double Amount) {
		triggerCon = triggerCondition;
		amount = String.valueOf(Amount);
	}
	
	@Override
	public String getTemplateFile() {
		return "checkPlayerHealth.ftlh";
	}

	@Override
	public Map<String, Object> getTempMappings() {
		Map<String, Object> temp = new HashMap<>();
		
		temp.put("HealthCondition", triggerCon);
		temp.put("HealthAmount", amount);
		
		return temp;
	}

	@Override
	public List<String> getImports() {
		return null;
	}
	
}