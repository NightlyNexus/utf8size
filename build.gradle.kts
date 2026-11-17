plugins {
  kotlin("multiplatform") version "2.4.21"
}

group = "com.nightlynexus"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

kotlin {
  js {
    binaries.executable()
    browser()
  }

  sourceSets {
    val jsMain = getByName("jsMain") {
      dependencies {
        implementation("com.squareup.okio:okio-nodefilesystem:3.18.2")
        implementation("com.squareup.okio:okio:3.18.2")
      }
    }
    val jsTest = getByName("jsTest") {
      dependencies {
        implementation(kotlin("test"))
      }
    }
  }
}
