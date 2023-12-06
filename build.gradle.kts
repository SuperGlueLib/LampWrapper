plugins {
    java
    `maven-publish`
    kotlin("jvm") version "1.9.0"
}

group = "com.github.supergluelib"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://www.jitpack.io")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.4-R0.1-SNAPSHOT")

    api("com.github.Revxrsal.Lamp:common:3.1.5")
    api("com.github.Revxrsal.Lamp:bukkit:3.1.5")
    api("com.github.Revxrsal.Lamp:brigadier:3.1.5")
}

kotlin {
    jvmToolchain(17)
}

publishing.publications.create<MavenPublication>("maven") {
    groupId = "com.github.supergluelib"
    artifactId = "SuperTeamsAPI"
    version = version

    from(components["java"])
}