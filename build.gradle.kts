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
    }
}


allprojects {
    repositories {
        google()
        mavenCentral()
    }
}