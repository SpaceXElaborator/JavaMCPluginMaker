package com.terturl.PluginMaker.Effects.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.terturl.PluginMaker.Effects.PluginEffect;

public class PlayerTeleportSpecificEffect extends PluginEffect {

	private String world;
	private String x;
	private String y;
	private String z;
	
	public PlayerTeleportSpecificEffect(String World, Double X, Double Y, Double Z) {
		world = World;
		
		x = String.valueOf(X);
		y = String.valueOf(Y);
		z = String.valueOf(Z);
		
		this.requiresPlayer = true;
	}
	
	@Override
	public String getTemplateFile() {
		return "teleportPlayerSpecific.ftlh";
	}

	@Override
	public Map<String, Object> getTempMappings() {
		Map<String, Object> temp = new HashMap<>();
		temp.put("X", x);
		temp.put("Y", y);
		temp.put("Z", z);
		temp.put("World", world);
		return temp;
	}

	@Override
	public List<String> getImports() {
		List<String> imports = new ArrayList<>();
		
		imports.add("org.bukkit.Location");
		imports.add("org.bukkit.Bukkit");
		
		return imports;
	}
	
}