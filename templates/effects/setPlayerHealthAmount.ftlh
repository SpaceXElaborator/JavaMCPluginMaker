<#if TriggerCanonicalName?has_content>
	<#assign LocalHealth = TriggerCanonicalName + CanonicalName + "Effect_HealthAmount">
	<#assign LocalHealType = TriggerCanonicalName + CanonicalName + "Effect_HealthType">
<#else>
	<#assign LocalHealth = CanonicalName + "Effect_HealthAmount">
	<#assign LocalHealType = CanonicalName + "Effect_HealthType">
</#if>

<#if EffectVariables[LocalHealType]=="Set">
p.setHealth((Double) ${EffectVariables[LocalHealth]});
<#elseif EffectVariables[LocalHealType]=="Add">
p.setHealth(p.getHealth() + (Double) ${EffectVariables[LocalHealth]});
<#else>
p.setHealth(p.getHealth() - (Double) ${EffectVariables[LocalHealth]});
</#if>