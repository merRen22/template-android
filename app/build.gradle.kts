plugins {
  id("com.android.application")
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
    applicationId = AppConfig.packageName
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

  val commonCompilerArgs = listOfNotNull(
    "-Xallow-jvm-ir-dependencies",
    "-Xskip-prerelease-check"
  )

  kotlinOptions {
    jvmTarget = "1.8"
    // TODO evaluate usage
    freeCompilerArgs = commonCompilerArgs
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = Versions.Dependencies.Compose
  }
  packagingOptions {
    exclude("META-INF/*")
  }
  //dynamicFeatures = [':dynamic:sample']
}

dependencies {

  implementation(Dependencies.Compose.composeActivity)
  implementation(Dependencies.Accompanist.insets)
  implementation(Dependencies.Androidx.SplashScreen)

  // libs
  implementShared()
  implementCompose()
  implementTest()
  implementAndroidTest()

  // module
  implementation(project(":base"))
  implementation(project(":baseUI"))
  implementation(project(":feature:about"))
  //implementation(project(":feature:search"))
  //implementation(project(":feature:pin"))
}