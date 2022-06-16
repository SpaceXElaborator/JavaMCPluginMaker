<#assign Effects = TriggerVariables[TriggerCanonicalName + "Effects"]>
<#assign Triggers = TriggerVariables[TriggerCanonicalName + "Triggers"]>
<#if TriggerVariables[TriggerCanonicalName + "Trigger_HealthCondition"] == ">">
if(p.getHealth() > (Double) ${TriggerVariables[TriggerCanonicalName + "Trigger_HealthAmount"]}) {
<#elseif TriggerVariables[TriggerCanonicalName + "Trigger_HealthCondition"] == "==">
if(p.getHealth() == (Double) ${TriggerVariables[TriggerCanonicalName + "Trigger_HealthAmount"]}) {
<#elseif TriggerVariables[TriggerCanonicalName + "Trigger_HealthCondition"] == ">=">
if(p.getHealth() >= (Double) ${TriggerVariables[TriggerCanonicalName + "Trigger_HealthAmount"]}) {
<#elseif TriggerVariables[TriggerCanonicalName + "Trigger_HealthCondition"] == "<=">
if(p.getHealth() <= (Double) ${TriggerVariables[TriggerCanonicalName + "Trigger_HealthAmount"]}) {
<#elseif TriggerVariables[TriggerCanonicalName + "Trigger_HealthCondition"] == "<">
if(p.getHealth() <= (Double) ${TriggerVariables[TriggerCanonicalName + "Trigger_HealthAmount"]}) {
<#else>
if(p.getHealth() != (Double) ${TriggerVariables[TriggerCanonicalName + "Trigger_HealthAmount"]}) {
</#if>
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