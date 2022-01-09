plugins {
  java
  kotlin("jvm") version "1.5.21"
  kotlin("plugin.serialization") version "1.5.21"
  id("com.github.johnrengelman.shadow") version "7.1.1"
  id("io.itsusinn.pkg") version "0.1.0-dev36"
}
repositories {
  mavenCentral()
  google()
  maven("https://jitpack.io")
}

pkg {
  relocate("kotlin", "shadow.kotlin")
  shadowJar {
    manifest {
      attributes(
        "Main-Class" to "MainKt"
      )
    }
  }
}

tasks.compileKotlin {
  kotlinOptions {
    jvmTarget = "1.8"
    freeCompilerArgs = listOf("-Xinline-classes", "-Xopt-in=kotlin.RequiresOptIn")
  }
  sourceCompatibility = "1.8"
}

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-cbor:1.2.2")
  implementation("io.ktor:ktor-client-core:1.5.4")
  implementation("io.ktor:ktor-client-cio-jvm:1.5.4")
}
