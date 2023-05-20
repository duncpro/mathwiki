plugins {
    kotlin("js") version "1.8.21"
}

group = "com.duncpro.mathwiki"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(npm("katex", "0.16.7"))
    implementation("com.duncpro.webk:webk:1.9")
    testImplementation(kotlin("test"))
}

kotlin {
    js {
        binaries.executable()
        browser {
            commonWebpackConfig {
                devtool = "source-map"
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }
}