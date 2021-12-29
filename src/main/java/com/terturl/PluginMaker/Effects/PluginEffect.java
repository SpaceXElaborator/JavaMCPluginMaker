package com.terturl.PluginMaker.Effects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.terturl.PluginMaker.Triggers.PluginTrigger;

public abstract class PluginEffect {
	
	private String canonicalName;
	public boolean requiresPlayer = false;
	
	public abstract String getTemplateFile();
	public abstract Map<String, Object> getTempMappings();
	public abstract List<String> getImports();
	
	public String getCanonicalName() {
		return canonicalName;
	}
	
	public void setCanonicalName(String s) {
		canonicalName = s;
	}
	
	public Map<String, Object> getMappings(PluginTrigger pt) {
		Map<String, Object> temp = new HashMap<>();
		String ptName = (pt != null) ? pt.getCanonicalName() : "";
		getTempMappings().forEach((k, v) -> {
			temp.put(ptName + getCanonicalName() + "Effect_" + k, v);
		});
		return temp;
	}
	
}