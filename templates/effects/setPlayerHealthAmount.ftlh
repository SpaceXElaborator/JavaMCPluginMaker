<#if TriggerCanonicalName?has_content>
	<#assign LocalHealth = TriggerCanonicalName + CanonicalName + "Effect_HealthAmount">
	<#assign LocalHealType = TriggerCanonicalName + CanonicalName + "Effect_HealthType">
	<#assign LocalCanMax = TriggerCanonicalName + CanonicalName + "Effect_CanMax">
	<#assign LocalCanKill = TriggerCanonicalName + CanonicalName + "Effect_CanKill">
<#else>
	<#assign LocalHealth = CanonicalName + "Effect_HealthAmount">
	<#assign LocalHealType = CanonicalName + "Effect_HealthType">
	<#assign LocalCanMax = CanonicalName + "Effect_CanMax">
	<#assign LocalCanKill = CanonicalName + "Effect_CanKill">
</#if>
<#if EffectVariables[LocalHealType]=="Set">
p.setHealth((Double) ${EffectVariables[LocalHealth]});
<#elseif EffectVariables[LocalHealType]=="Add">
<#if EffectVariables[LocalCanMax]>
if(p.getHealth() + (Double) ${EffectVariables[LocalHealth]} > p.getMaxHealth()) {
	p.setHealth(p.getMaxHealth());
} else {
	p.setHealth(p.getHealth() + (Double) ${EffectVariables[LocalHealth]});
}
<#else>
p.setHealth(p.getHealth() + (Double) ${EffectVariables[LocalHealth]});
</#if>
<#else>
<#if EffectVariables[LocalCanKill]>
if(p.getHealth() - (Double) ${EffectVariables[LocalHealth]} < 0) {
	p.setHealth(0);
} else {
	p.setHealth(p.getHealth() - (Double) ${EffectVariables[LocalHealth]});
}
<#else>
p.setHealth(p.getHealth() - (Double) ${EffectVariables[LocalHealth]});
</#if>
</#if>