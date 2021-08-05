plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
  id("dagger.hilt.android.plugin")
}

apply {
  from("$rootDir/ktlint.gradle.kts")
}

android {
  compileSdkVersion(AppConfig.compileSdkVersion)
  defaultConfig {
    minSdkVersion(AppConfig.minSdkVersion)
    targetSdkVersion(AppConfig.targetSdkVersion)
    versionCode = AppConfig.versionCode
    versionName = AppConfig.versionName
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      isDebuggable = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
    getByName("debug") {
      isDebuggable = true
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    // Flag to enable support for the new language APIs 👇
    // https://developer.android.com/studio/write/java8-support
    isCoreLibraryDesugaringEnabled = true
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerVersion = Versions.Project.kotlin_version
    kotlinCompilerExtensionVersion = Versions.Dependencies.Compose
  }
}

dependencies {

  implementShared()
  implementCompose()

}