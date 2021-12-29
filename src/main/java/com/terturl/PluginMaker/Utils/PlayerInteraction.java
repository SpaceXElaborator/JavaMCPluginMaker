package com.terturl.PluginMaker.Utils;

public enum PlayerInteraction {

	LEFTCLICKBLOCK("LeftClickBlock", "LEFT_CLICK_BLOCK"),
	LEFTCLICKAIR("LeftClickAir", "LEFT_CLICK_AIR"),
	RIGHTCLICKBLOCK("RightClickBlock", "RIGHT_CLICK_BLOCK"),
	RIGHTCLICKAIR("RightClickAir", "RIGHT_CLICK_AIR");

	private String friendlyName;
	private String javaName;
	
	PlayerInteraction(String string, String s2) {
		friendlyName = string;
		javaName = s2;
	}
	
	public String getFriendlyName() {
		return friendlyName;
	}
	
	public String getJavaName() {
		return javaName;
	}
	
}