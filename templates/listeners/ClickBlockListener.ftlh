package com.${Plugin.author}.${Plugin.packageName}.Listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
<#if RequiresPlayer>import org.bukkit.entity.Player;</#if>
<#if FileImports?has_content>
<#list FileImports as fi>
import ${fi};
</#list>
</#if>

public class ${Name}Listener implements Listener {

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if(!e.getAction().equals(Action.${ListenerAction})) return;
		<#if ListenerBlock?has_content>if(!e.getClickedBlock().getType().getMaterial().equals(Material.${ListenerBlock})) return;</#if>
		
		<#if RequiresPlayer>Player p = e.getPlayer();</#if>
		
		<#if LocalEffects?has_content>
		<#list LocalEffects as e>
		<#assign fileName = e.getTemplateFile()>
		<#global CanonicalName = e.getCanonicalName()>
		<#include "../effects/" + fileName>
		</#list>
		</#if>
		
		<#if Triggers?has_content>
		<#list Triggers as e>
		<#assign fileName = e.getTemplateFile()>
		<#global TriggerCanonicalName = e.getCanonicalName()>
		<#include "../triggers/" + fileName>
		</#list>
		</#if>
	}
}