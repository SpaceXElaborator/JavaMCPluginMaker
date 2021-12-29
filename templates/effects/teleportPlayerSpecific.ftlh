<#if TriggerCanonicalName?has_content>
	<#assign LocalX = TriggerCanonicalName + CanonicalName + "Effect_X">
	<#assign LocalY = TriggerCanonicalName + CanonicalName + "Effect_Y">
	<#assign LocalZ = TriggerCanonicalName + CanonicalName + "Effect_Z">
	<#assign LocalWorld = TriggerCanonicalName + CanonicalName + "Effect_World">
<#else>
	<#assign LocalX = CanonicalName + "Effect_X">
	<#assign LocalY = CanonicalName + "Effect_Y">
	<#assign LocalZ = CanonicalName + "Effect_Z">
	<#assign LocalWorld = CanonicalName + "Effect_World">
</#if>
p.teleport(new Location(Bukkit.getWorld("${EffectVariables[LocalWorld]}"), (Double)${EffectVariables[LocalX]}, (Double)${EffectVariables[LocalY]}, (Double)${EffectVariables[LocalZ]}));