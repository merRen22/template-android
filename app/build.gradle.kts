plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
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
    kotlinOptions {
        jvmTarget = "1.8"
        // TODO evaluate usage
        //freeCompilerArgs += ["-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check"]
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerVersion = Versions.Project.kotlin_version
        kotlinCompilerExtensionVersion = Versions.Dependencies.Compose
    }
    //dynamicFeatures = [':dynamic:sample']
}

dependencies {

    // libs
    implementShared()
    implementCompose()
    implementTest()
    implementAndroidTest()

    // module
    //implementation(project(":base"))
    //implementation(project(":feature:feed"))
    //implementation(project(":feature:search"))
    //implementation(project(":feature:pin"))
}