package com.${plugin.author}.${plugin.packageName};

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface CommandMeta {

	// Can be used to show what the command does when doing /help
	String description() default "";

	// Can be used to have shortcuts to commands (/TestCommand -> /tc)
	String[] aliases() default {};

	// Can be used to show how you'd perform the command (/{command} {player} {ban/kick})
	String usage() default "";
}
