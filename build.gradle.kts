buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Plugins.pluginBuildGradle)
        classpath(Plugins.pluginKotlinGradle)
        classpath(Plugins.pluginSafeArgs)
        classpath(Plugins.pluginDaggerHilt)
        classpath(Plugins.pluginKotlinSerialization)
      classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30")
    }
}


allprojects {
    repositories {
        google()
        mavenCentral()
    }
}