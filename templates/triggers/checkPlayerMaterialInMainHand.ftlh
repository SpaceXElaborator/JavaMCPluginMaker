<#assign Effects = TriggerVariables[TriggerCanonicalName + "Effects"]>
if(e.getHand().equals(EquipmentSlot.HAND) && e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.${TriggerVariables[TriggerCanonicalName + "Trigger_Material"]})) {
	<#if Effects?has_content>
	<#list Effects as e>
	<#assign fileName = e.getTemplateFile()>
	<#global CanonicalName = e.getCanonicalName()>
	<#include "../effects/" + fileName>
	</#list>
	</#if>
}