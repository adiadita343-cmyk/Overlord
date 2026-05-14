plugins {
    kotlin("jvm") version "2.0.0"
    id("fabric-loom") version "1.7-SNAPSHOT"
    id("maven-publish")
}

version = "1.0.0"
group = "net.overlord"

base {
    archivesName.set("overlord")
}

repositories {
    // Locul de unde Gradle descarcă librăriile necesare
    mavenCentral()
}

dependencies {
    // Versiunile necesare pentru Minecraft 1.21.1
    minecraft("com.mojang:minecraft:1.21.1")
    mappings("net.fabricmc:yarn:1.21.1+build.3:v2")
    modImplementation("net.fabricmc:fabric-loader:0.15.11")

    // Fabric API - esențial pentru majoritatea modurilor
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.102.0+1.21.1")
    
    // Kotlin pentru Fabric
    modImplementation("net.fabricmc:fabric-language-kotlin:1.11.0+kotlin.2.0.0")
}

tasks.processResources {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${project.base.archivesName.get()}" }
    }
}
