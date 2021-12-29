<#if TriggerCanonicalName?has_content>
	<#assign LocalMaterial = TriggerCanonicalName + CanonicalName + "Effect_Material">
	<#assign LocalAmount = TriggerCanonicalName + CanonicalName + "Effect_Amount">
<#else>
	<#assign LocalMaterial = CanonicalName + "Effect_Material">
	<#assign LocalAmount = CanonicalName + "Effect_Amount">
</#if>
p.getInventory().addItem(new ItemStack(Material.${EffectVariables[LocalMaterial]}, (Integer)${EffectVariables[LocalAmount]}));