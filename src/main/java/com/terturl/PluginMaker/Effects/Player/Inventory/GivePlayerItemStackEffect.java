package com.terturl.PluginMaker.Effects.Player.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.terturl.PluginMaker.Effects.PluginEffect;

public class GivePlayerItemStackEffect extends PluginEffect {

	private String material;
	private Integer amount;
	
	public GivePlayerItemStackEffect(String Mat, Integer Amount) {
		material = Mat;
		amount = Amount;
		this.requiresPlayer = true;
	}
	
	@Override
	public String getTemplateFile() {
		return "./givePlayerItemStack.ftlh";
	}

	@Override
	public Map<String, Object> getTempMappings() {
		Map<String, Object> temp = new HashMap<>();
		
		temp.put("Material", material);
		temp.put("Amount", amount);
		
		return temp;
	}

	@Override
	public List<String> getImports() {
		List<String> imports = new ArrayList<>();
		
		imports.add("org.bukkit.Material");
		imports.add("org.bukkit.inventory.ItemStack");
		
		return imports;
	}
	
}