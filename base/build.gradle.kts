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
}

dependencies {

  implementTest()
  implementShared()

  // module
  api(project(":data:repository"))

  // design/core
  api(Dependencies.Androidx.AppCompat)
  api(Dependencies.Androidx.Material)
  api(Dependencies.Androidx.ConstraintLayout)
  api(Dependencies.Androidx.CoreKtx)
  api(Dependencies.Androidx.ActivityKtx)
  api(Dependencies.Androidx.FragmentKtx)

  // lifecycle
  api(Dependencies.Lifecycle.lifecycleRuntime)
  api(Dependencies.Lifecycle.lifecycleExtensions)
  kapt(Dependencies.Lifecycle.lifecycleCompiler)
  api(Dependencies.Lifecycle.viewmodelKtx)
  api(Dependencies.Lifecycle.livedataKtx)

  // navigation
  api(Dependencies.Navigation.navCommonKtx)
  api(Dependencies.Navigation.navRuntimeKtx)
  api(Dependencies.Navigation.navFragmentKtx)
  api(Dependencies.Navigation.navUiKtx)
  api(Dependencies.Navigation.navDfm)
}