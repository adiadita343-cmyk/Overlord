plugins {
    id("fabric-loom") version "1.8-SNAPSHOT"
    id("maven-publish")
}

version = "1.0.0"
group = "net.overlord"

repositories {
    maven { url = uri("https://maven.fabricmc.net/") }
    mavenCentral()
}

dependencies {
    // Versiunea de Minecraft
    minecraft("com.mojang:minecraft:1.21.1")
    
    // Mappings (Yarn)
    mappings("net.fabricmc:yarn:1.21.1+build.3:v2")
    
    // Fabric Loader
    modImplementation("net.fabricmc:fabric-loader:0.15.11")

    // Fabric API (Opțional, dar recomandat pentru majoritatea modurilor)
    // modImplementation("net.fabricmc.fabric-api:fabric-api:0.102.0+1.21.1")
}

tasks.processResources {
    inputs.property("version", project.version)
    filteringCharset = "UTF-8"

    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
    options.encoding = "UTF-8"
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${project.name}" }
    }
}
