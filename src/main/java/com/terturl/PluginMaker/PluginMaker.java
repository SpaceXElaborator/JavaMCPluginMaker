package com.terturl.PluginMaker;

import java.io.File;
import java.io.IOException;

import com.terturl.PluginMaker.Effects.Player.SendPlayerMessageEffect;
import com.terturl.PluginMaker.Listeners.Listener;
import com.terturl.PluginMaker.Listeners.Blocks.InteractBlockListener;
import com.terturl.PluginMaker.Listeners.Player.PlayerJoinListener;
import com.terturl.PluginMaker.Plugin.Plugin;
import com.terturl.PluginMaker.Triggers.PluginTrigger;
import com.terturl.PluginMaker.Triggers.Player.CheckPlayerItemInHand;
import com.terturl.PluginMaker.Utils.CodeFormatter;
import com.terturl.PluginMaker.Utils.PlayerInteraction;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class PluginMaker {

	private static Configuration conf;
	private static CodeFormatter codeFormatter;
	public static File LISTENER_TEMPLATES = new File("./templates/listeners/");
	public static File EFFECTS_TEMPLATES = new File("./templates/effects/");
	public static File MAIN_TEMPLATE_DIR = new File("./templates/");
	
	public static void main(String[] args) throws IOException {
		
		codeFormatter = new CodeFormatter();
		
		File f = new File("./templates");
		f.mkdir();
		
		conf = new Configuration(Configuration.VERSION_2_3_31);
		conf.setDirectoryForTemplateLoading(f);
		conf.setDefaultEncoding("UTF-8");
		conf.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		conf.setLogTemplateExceptions(false);
		conf.setWrapUncheckedExceptions(true);
		conf.setWrapUncheckedExceptions(true);
		conf.setFallbackOnNullLoopVariable(false);
		
		try {
			createTestTemplate();
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
	}
	
	public static void createTestTemplate() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Plugin p = new Plugin("Plugin Maker", "terturl");
		
		Listener l1 = new InteractBlockListener(PlayerInteraction.RIGHTCLICKAIR, "");
		PluginTrigger pt = new CheckPlayerItemInHand("STICK");
		pt.addPluginEffect(new SendPlayerMessageEffect("This is a test"));
		l1.addPluginTrigger(pt);
		
		Listener l2 = new PlayerJoinListener("PlayerJoinGame");
		l2.addListenerEffect(new SendPlayerMessageEffect("Welcome to the server!"));
		
		p.getListeners().add(l1);
		p.getListeners().add(l2);
		
		p.create();
	}
	
	public static Configuration getConf() {
		return conf;
	}
	
	public static CodeFormatter getCodeFormatter() {
		return codeFormatter;
	}
	
}