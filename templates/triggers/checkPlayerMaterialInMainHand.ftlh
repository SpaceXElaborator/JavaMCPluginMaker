<#if TriggerVariables[TriggerCanonicalName + "Effects"]?has_content>
<#assign Effects = TriggerVariables[TriggerCanonicalName + "Effects"]>
</#if>
<#if TriggerVariables[TriggerCanonicalName + "Triggers"]?has_content>
<#assign Triggers = TriggerVariables[TriggerCanonicalName + "Triggers"]>
</#if>
if(e.getHand().equals(EquipmentSlot.HAND) && e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.${TriggerVariables[TriggerCanonicalName + "Trigger_Material"]})) {
	<#if Triggers?has_content>
	<#list Triggers as e>
	<#assign fileName = e.getTemplateFile()>
	<#global TriggerCanonicalName = e.getCanonicalName()>
	<#include "../triggers/" + fileName>
	</#list>
	</#if>
	
	<#if Effects?has_content>
	<#list Effects as e>
	<#assign fileName = e.getTemplateFile()>
	<#global CanonicalName = e.getCanonicalName()>
	<#include "../effects/" + fileName>
	</#list>
	</#if>
}