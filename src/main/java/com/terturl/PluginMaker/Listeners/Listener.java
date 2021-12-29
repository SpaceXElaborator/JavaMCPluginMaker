package com.terturl.PluginMaker.Listeners;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.terturl.PluginMaker.PluginMaker;
import com.terturl.PluginMaker.Effects.PluginEffect;
import com.terturl.PluginMaker.Plugin.Plugin;
import com.terturl.PluginMaker.Triggers.PluginTrigger;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public abstract class Listener {

	private String name;
	private String tmp;
	
	private List<PluginEffect> listenerEffects = new ArrayList<>();
	private List<PluginTrigger> pluginTriggers = new ArrayList<>();
	
	public Listener(String s, String template) {
		name = s;
		tmp = template;
	}
	
	public abstract Map<String, Object> getMappings();
	
	public String getName() {
		return name;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public void addListenerEffect(PluginEffect pe) {
		pe.setCanonicalName("Effect" + String.valueOf(listenerEffects.size()));
		listenerEffects.add(pe);
	}
	
	public void addPluginTrigger(PluginTrigger pt) {
		pt.setCanonicalName("Trigger" + String.valueOf(pluginTriggers.size()));
		pluginTriggers.add(pt);
	}
	
	public void removeListenerEffect(PluginEffect pe) {
		listenerEffects.remove(pe);
	}
	
	public void removePluginTrigger(PluginTrigger pt) {
		pluginTriggers.remove(pt);
	}
	
	public List<PluginEffect> getListenerEffects() {
		return listenerEffects;
	}
	
	public List<PluginTrigger> getPluginTriggers() {
		return pluginTriggers;
	}
	
	public String getTemplate() {
		return tmp;
	}
	
	public Map<String, Object> defaultMappings(Plugin p) {
		Map<String, Object> listenerRoot = new HashMap<>();
		listenerRoot.put("Plugin", p);
		listenerRoot.put("FileImports", getImports());
		listenerRoot.put("LocalEffects", listenerEffects);
		listenerRoot.put("Triggers", getPluginTriggers());
		listenerRoot.put("Name", getName());
		listenerRoot.put("RequiresPlayer", checkForRequiresPlayer());
		listenerRoot.put("TriggerVariables", createMappingsFromTriggers());
		listenerRoot.put("EffectVariables", createMappingsForEffects());
		return listenerRoot;
	}
	
	public void create(Plugin p) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		File f = new File(p.getListenersFolder(), getName() + "Listener.java");
		if(f.exists()) f.delete();
		
		Map<String, Object> listenerRoot = defaultMappings(p);
		listenerRoot.putAll(getMappings());
		
		Writer out = new StringWriter();
		Template t = PluginMaker.getConf().getTemplate(tmp);
		t.process(listenerRoot, out);
		String formattedCode = PluginMaker.getCodeFormatter().reformatCode(out.toString());
		
		FileWriter writer = new FileWriter(f);
		writer.write(formattedCode);
		writer.close();
	}
	
	private boolean checkForRequiresPlayer() {
		for(PluginEffect pe : getListenerEffects()) {
			if(pe.requiresPlayer) return true;
		}
		for(PluginTrigger pt : getPluginTriggers()) {
			if(pt.requiresPlayer()) return true;
		}
		return false;
	}
	
	private Map<String, Object> createMappingsFromTriggers() {
		Map<String, Object> temp = new HashMap<>();
		for(PluginTrigger te : getPluginTriggers()) {
			temp.putAll(te.getMappings());
		}
		return temp;
	}
	
	private Map<String, Object> createMappingsForEffects() {
		Map<String, Object> temp = new HashMap<>();
		for(PluginEffect pe : getListenerEffects()) {
			temp.putAll(pe.getMappings(null));
		}
		for(PluginTrigger te : getPluginTriggers()) {
			temp.putAll(te.createMappingsFromEffects());
		}
		return temp;
	}
	
	private List<String> getImports() {
		List<String> imports = new ArrayList<>();
		
		for(PluginEffect pe : getListenerEffects()) {
			imports.addAll(pe.getImports().stream().filter(e -> !imports.contains(e)).collect(Collectors.toList()));
		}
		
		for(PluginTrigger te : getPluginTriggers()) {
			imports.addAll(te.getImports().stream().filter(e -> !imports.contains(e)).collect(Collectors.toList()));
		}
		
		return imports;
	}
	
}