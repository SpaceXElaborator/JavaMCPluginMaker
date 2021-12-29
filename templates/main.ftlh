package com.${Plugin.author}.${Plugin.packageName};

<#if Plugin.commands?has_content>import com.${Plugin.author}.${Plugin.packageName}.Commands.CommandHelpers.CommandMeta;</#if>
<#if Plugin.listeners?has_content>
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
<#list Plugin.listeners as l>
import com.${Plugin.author}.${Plugin.packageName}.Listeners.${l.getName() + "Listener"};
</#list>
</#if>
import org.bukkit.plugin.java.JavaPlugin;

public class Main${Plugin.packageName} extends JavaPlugin {

	private static Main${Plugin.packageName} instance;

	public void onEnable() {
		instance = this;
		
		<#if Plugin.commands?has_content>
		// ##### Registering Commands #####
		<#list Plugin.commands as command>
		registerCommand(new ${command.name}Command());
		</#list>
		// ################################
		</#if>
		<#if Plugin.listeners?has_content>
		
		// ##### Registering Listeners #####
		<#list Plugin.listeners as listener>
		registerListener(new ${listener.name}Listener());
		</#list>
		// #################################
		</#if>
	}
	<#if Plugin.listeners?has_content>
	
	public static <T extends Listener> T registerListener(T listener) {
		Bukkit.getPluginManager().registerEvents((Listener)listener, (Plugin) instance);
		return listener;
	}</#if>
	<#if Plugin.commands?has_content>
	public void registerCommand(CMCommand cmd) {
		
	}
	</#if>
	<#if Plugin.commands?has_content>
	
	// Boilerpoint code to register commands into Bukkit/Spigot WITHOUT having to write them into the YAML file
	public <T extends MCCommand> void registerCommand(T command) {
		PluginCommand pg = mc.getCommand(command.getName());
		
		// If the PluginCommand is null, we need to create it
		if(pg == null) {
			try {
				Constructor<PluginCommand> pluginConst = PluginCommand.class.getDeclaredConstructor(new Class[] { String.class, Plugin.class });
				pluginConst.setAccessible(true);
				pg = pluginConst.newInstance(new Object[] { command.getName(), mc });
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			// Get the Bukkit/Spigot CommandMap to add our custom command to the plugin
			CommandMap cm = null;
			PluginManager pm = Bukkit.getPluginManager();
			
			// Try and catch for accessing the private field commandMap
			try {
				Field cMapField = pm.getClass().getDeclaredField("commandMap");
				cMapField.setAccessible(true);
				cm = (CommandMap) cMapField.get(pm);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
			// An optional Java Annotation that we can put over our Custom Command class
			// To add aliases, description, and usage of the plugin
			CommandMeta anno = command.getClass().<CommandMeta>getAnnotation(CommandMeta.class);
			if(anno != null) {
				pg.setAliases(Arrays.asList(anno.aliases()));
				pg.setDescription(anno.description());
				pg.setUsage(anno.usage());
			}
			
			// Finally, we register the command with Bukkit/Spigot
			cm.register(mc.getDescription().getName(), (Command)pg);
		}
		
		// Set the Executor and TabCompleter
		pg.setExecutor((CommandExecutor) command);
		pg.setTabCompleter((TabCompleter) command);
		
		// Send a message to Bukkit/Spigot saying the command has been added for logging reasons
		mc.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[" + ChatColor.BLUE + "CommandManager" + ChatColor.GREEN + "]" + ChatColor.RED + " - "  + ChatColor.GOLD + "Registering Command " + ChatColor.DARK_GREEN + command.getName());
	}
	</#if>
}
