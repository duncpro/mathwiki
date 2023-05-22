plugins {
    kotlin("js") version "1.8.21"
    idea
    id("org.jetbrains.reflekt") version "1.5.0"
}

group = "com.duncpro.mathwiki"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven(url = uri("https://packages.jetbrains.team/maven/p/reflekt/reflekt"))
}

dependencies {
    implementation("org.jetbrains.reflekt", "reflekt-dsl", "1.5.0")
    implementation(npm("katex", "0.16.7"))
    implementation("com.duncpro.webk:webk:1.25")
    testImplementation(kotlin("test"))
}

kotlin {
    js {
        binaries.executable()
        browser {
            commonWebpackConfig {
                devtool = "source-map"
                devServer?.open = false
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }
}

idea {
    module {
        isDownloadSources = true
    }
}