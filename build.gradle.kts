plugins {
    kotlin("js") version "1.8.21"
    idea

}

group = "com.duncpro.mathwiki"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(npm("katex", "0.16.7"))
    implementation("com.duncpro.webk:webk:1.27")
    testImplementation(kotlin("test"))
}

kotlin {
    js(IR) {
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