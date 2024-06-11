plugins {
  kotlin("js") version "2.0.0"
}

group = "com.nightlynexus"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation("com.squareup.okio:okio-nodefilesystem:3.9.0")
  implementation("com.squareup.okio:okio:3.9.0")
  testImplementation(kotlin("test"))
}

kotlin {
  js {
    binaries.executable()
    browser()
  }
}
