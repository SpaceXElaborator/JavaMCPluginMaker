package com.terturl.PluginMaker.Commands;

public class Command {

	private String slash;
	private String name;
	
	public Command(String s, String n) {
		slash = s;
		name = n;
	}
	
	public String getSlash() {
		return slash;
	}
	
	public void setSlash(String s) {
		slash = s;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String s) {
		name = s;
	}
	
}