package com.terturl.PluginMaker.Utils;

import java.util.Properties;

import org.jboss.forge.roaster.Roaster;

public class CodeFormatter {

	private final Properties formatProperties;
	
	public CodeFormatter() {
		formatProperties = new Properties();
		formatProperties.setProperty("org.eclipse.jdt.core.compiler.source", "16");
		formatProperties.setProperty("org.eclipse.jdt.core.formatter.lineSplit", "150");
		formatProperties.setProperty("org.eclipse.jdt.core.formatter.blank_lines_before_imports", "1");
		formatProperties.setProperty("org.eclipse.jdt.core.formatter.blank_lines_after_imports", "1");
		formatProperties.setProperty("org.eclipse.jdt.core.formatter.blank_lines_after_package", "1");
		formatProperties.setProperty("org.eclipse.jdt.core.formatter.blank_lines_between_type_declarations", "1");
		formatProperties.setProperty("org.eclipse.jdt.core.formatter.blank_lines_before_method", "1");
		formatProperties.setProperty("org.eclipse.jdt.core.formatter.blank_lines_after_method", "1");
		formatProperties.setProperty("org.eclipse.jdt.core.formatter.blank_lines_before_member_type", "1");
		formatProperties.setProperty("org.eclipse.jdt.core.formatter.number_of_empty_lines_to_preserve", "1");
		formatProperties.setProperty("org.eclipse.jdt.core.formatter.blank_lines_before_new_chunk", "1");
		formatProperties.setProperty("org.eclipse.jdt.core.formatter.join_wrapped_lines", "true");

		formatProperties.setProperty("org.eclipse.jdt.core.formatter.tabulation.char", "tab");

		formatProperties.setProperty("org.eclipse.jdt.core.formatter.comment.format_block_comments", "false");
		formatProperties.setProperty("org.eclipse.jdt.core.formatter.comment.format_javadoc_comments", "false");
		formatProperties.setProperty("org.eclipse.jdt.core.formatter.comment.format_line_comments", "false");
	}
	
	public String reformatCode(String code) {
		return Roaster.format(formatProperties, code);
	}
	
}