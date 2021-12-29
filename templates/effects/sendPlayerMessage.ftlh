<#if TriggerCanonicalName?has_content>
	<#assign Message = TriggerCanonicalName + CanonicalName + "Effect_Message">
<#else>
	<#assign Message = CanonicalName + "Effect_Message">
</#if>
p.sendMessage("${EffectVariables[Message]}");