package com.terturl.PluginMaker.Triggers.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.terturl.PluginMaker.Triggers.PluginTrigger;

public class CheckPlayerItemInHand extends PluginTrigger {

	private String material;
	
	public CheckPlayerItemInHand(String Material) {
		material = Material;
	}
	
	@Override
	public String getTemplateFile() {
		return "checkPlayerMaterialInMainHand.ftlh";
	}

	@Override
	public Map<String, Object> getTempMappings() {
		Map<String, Object> mapping = new HashMap<String, Object>();
		mapping.put("Material", material);
		return mapping;
	}

	@Override
	public List<String> getImports() {
		List<String> imports = new ArrayList<>();
		
		imports.add("org.bukkit.inventory.EquipmentSlot");
		imports.add("org.bukkit.Material");
		imports.addAll(getEffectImports().stream().filter(e -> !imports.contains(e)).collect(Collectors.toList()));
		
		return imports;
	}
	
}