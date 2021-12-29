package com.terturl.PluginMaker.Triggers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.terturl.PluginMaker.Effects.PluginEffect;

public abstract class PluginTrigger {

	private String canonicalName;
	private List<PluginEffect> effects = new ArrayList<>();
	
	public abstract String getTemplateFile();
	public abstract Map<String, Object> getTempMappings();
	public abstract List<String> getImports();

	public String getCanonicalName() {
		return canonicalName;
	}

	public void setCanonicalName(String s) {
		canonicalName = s;
	}
	
	public void addPluginEffect(PluginEffect pe) {
		pe.setCanonicalName("Effect" + String.valueOf(effects.size()));
		effects.add(pe);
	}
	
	public boolean requiresPlayer() {
		for(PluginEffect pe : getEffects()) {
			if(pe.requiresPlayer) return true;
		}
		return false;
	}
	
	public List<PluginEffect> getEffects() {
		return effects;
	}
	
	public Map<String, Object> getMappings() {
		Map<String, Object> temp = new HashMap<>();
		temp.put(getCanonicalName() + "Effects", effects);
		getTempMappings().forEach((k, v) -> {
			temp.put(getCanonicalName() + "Trigger_" + k, v);
		});
		return temp;
	}

	public Map<String, Object> createMappingsFromEffects() {
		Map<String, Object> temp = new HashMap<>();
		for(PluginEffect pe : effects) {
			temp.putAll(pe.getMappings(this));
		}
		return temp;
	}
	
	public List<String> getEffectImports() {
		List<String> imports = new ArrayList<>();
		for(PluginEffect pe : getEffects()) {
			imports.addAll(pe.getImports().stream().filter(e -> !imports.contains(e)).collect(Collectors.toList()));
		}
		return imports;
	}
	
}