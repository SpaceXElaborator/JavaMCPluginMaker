package com.terturl.PluginMaker.Plugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.terturl.PluginMaker.PluginMaker;
import com.terturl.PluginMaker.Commands.Command;
import com.terturl.PluginMaker.Listeners.Listener;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class Plugin {

	private String name;
	private String formattedName;
	private String packageName;
	private String author;
	private String version;
	
	private List<Command> commands = new ArrayList<>();
	private List<Listener> listeners = new ArrayList<>();
	
	private File mainFolder;
	private File codeFolder;
	private File commandsFolder;
	private File listenersFolder;
	
	public Plugin(String pName, String pAuthor) {
		name = pName;
		formattedName = pName.replaceAll(" ", "_").toLowerCase();
		packageName = pName.replace(" ", "");
		version = "0.0.1";
		author = pAuthor;
		
		mainFolder = new File("./Projects/" + formattedName);
		mainFolder.mkdirs();
		
		codeFolder = new File(mainFolder, "/src/main/java/com/" + author + "/" + packageName);
		codeFolder.mkdirs();
		
		commandsFolder = new File(codeFolder, "Commands");
		listenersFolder = new File(codeFolder, "Listeners");
		
		commandsFolder.mkdir();
		listenersFolder.mkdir();
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getFormattedName() {
		return formattedName;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getVersion() {
		return version;
	}
	
	public File getMainFolder() {
		return mainFolder;
	}
	
	public File getCommandsFolder() {
		return commandsFolder;
	}
	
	public File getListenersFolder() {
		return listenersFolder;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public void setFormattedName(String s) {
		formattedName = s;
	}
	
	public void setAuthor(String s) {
		author = s;
	}
	
	public void setVersion(String s) {
		version = s;
	}
	
	public void setPackageName(String s) {
		packageName = s;
	}
	
	public List<Command> getCommands() {
		return commands;
	}
	
	public List<Listener> getListeners() {
		return listeners;
	}
	
	public boolean hasListeners() {
		return listeners.size() > 0;
	}
	
	public void create() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		File f = new File(codeFolder, "Main" + getPackageName() + ".java");
		if(f.exists()) f.delete();
		
		Map<String, Object> root = new HashMap<>();
		root.put("Plugin", this);
		
		Template temp = PluginMaker.getConf().getTemplate("main.ftlh");
		Writer out = new StringWriter();
		temp.process(root, out);
		String formattedCode = PluginMaker.getCodeFormatter().reformatCode(out.toString());
		FileWriter writer = new FileWriter(f);
		writer.write(formattedCode);
		writer.close();
		
		createListeners();
		createPom();
		createYaml();
	}
	
	private void createListeners() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		for(Listener l : listeners) {
			l.create(this);
		}
	}
	
	private void createPom() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		File f = new File(mainFolder, "pom.xml");
		if(f.exists()) f.delete();
		
		Map<String, Object> root = new HashMap<>();
		root.put("Plugin", this);
		
		Template temp = PluginMaker.getConf().getTemplate("pomxml.ftlh");
		Writer out = new StringWriter();
		temp.process(root, out);
		String formattedCode = PluginMaker.getCodeFormatter().reformatCode(out.toString());
		FileWriter writer = new FileWriter(f);
		writer.write(formattedCode);
		writer.close();
	}
	
	private void createYaml() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		File f = new File(mainFolder, "/src/main/java/plugin.yml");
		if(f.exists()) f.delete();
		
		Map<String, Object> root = new HashMap<>();
		root.put("Plugin", this);
		
		Template temp = PluginMaker.getConf().getTemplate("pluginyaml.ftlh");
		Writer out = new StringWriter();
		temp.process(root, out);
		String formattedCode = PluginMaker.getCodeFormatter().reformatCode(out.toString());
		FileWriter writer = new FileWriter(f);
		writer.write(formattedCode);
		writer.close();
	}
	
}