plugins {
    id("fabric-loom") version "1.7.4"
    id("maven-publish")
    kotlin("jvm") version "2.0.21"
}

version = project.property("mod_version") as String
group = project.property("maven_group") as String

repositories {
    maven("https://maven.fabricmc.net/")
}

dependencies {
    minecraft("com.mojang:minecraft:${project.property("minecraft_version")}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_api_version")}")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.12.3+kotlin.2.0.21")
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}

kotlin {
    jvmToolchain(21)
}
