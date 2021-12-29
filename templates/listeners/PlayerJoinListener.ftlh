package com.${Plugin.author}.${Plugin.packageName}.Listeners;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
<#if RequiresPlayer>import org.bukkit.entity.Player;</#if>
<#if FileImports?has_content>
<#list FileImports as fi>
import ${fi};
</#list>
</#if>

public class ${Name}Listener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
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