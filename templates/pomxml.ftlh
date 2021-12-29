<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	
	<groupId>org.${Plugin.author}</groupId>
	<artifactId>${Plugin.formattedName}</artifactId>
	<version>${Plugin.version}</version>
	<name>${Plugin.name}</name>
	
	<properties>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
	</properties>
	
	<repositories>
		<!-- This adds the Spigot Maven repository to the build -->
		<repository>
			<id>spigot-repo</id>
			<url>https://hub.spigotmc.org/nexus/content/repositories/snapshots/</url>
		</repository>
	</repositories>
	
	<dependencies>
		<!--This adds the Spigot API artifact to the build -->
		<dependency>
			<groupId>org.spigotmc</groupId>
			<artifactId>spigot-api</artifactId>
			<version>1.18-R0.1-SNAPSHOT</version>
			<scope>provided</scope>
		</dependency>
	</dependencies>
	
	<build>
		<sourceDirectory>src/main/java</sourceDirectory>
		<resources>
			<resource>
				<directory>src/main/java</directory>
				<includes>
					<include>plugin.yml</include>
				</includes>
			</resource>
		</resources>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.8.1</version>
			</plugin>
			<plugin>
				<artifactId>maven-assembly-plugin</artifactId>
				<configuration>
					<archive>
						<manifest>
							<mainClass>
								com.${Plugin.author}.${Plugin.packageName}.Main${Plugin.packageName}
							</mainClass>
						</manifest>
					</archive>
					<descriptorRefs>
						<descriptorRef>jar-with-dependencies</descriptorRef>
					</descriptorRefs>
				</configuration>
			</plugin>
		</plugins>
	</build>
	
</project>
