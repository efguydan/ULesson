plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
    id("kotlin-android")
}

android {
    compileSdkVersion(Config.App.SdkVersions.compile)
    buildToolsVersion(Config.App.Versions.buildTools)

    defaultConfig {
        applicationId = Config.App.Variables.appID
        minSdkVersion(Config.App.SdkVersions.min)
        targetSdkVersion(Config.App.SdkVersions.target)
        versionCode = Config.App.Versions.code
        versionName = Config.App.Versions.name
        testInstrumentationRunner = Config.App.Variables.testRunner
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        useIR = true
    }

    androidExtensions {
        isExperimental = true
    }

    lintOptions {
        isAbortOnError = false
        isIgnoreWarnings = true
        isQuiet = true
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Config.Libs.Core.kotlinStd)
    implementation(Config.Libs.Core.material)
    implementation(Config.Libs.Core.coroutinesCore)
    implementation(Config.Libs.Core.coroutinesAndroid)

    implementation(Config.Libs.AndroidX.coreKtx)
    implementation(Config.Libs.AndroidX.appCompat)
    implementation(Config.Libs.AndroidX.constraintLayout)
    implementation(Config.Libs.AndroidX.lifeCycleExt)
    implementation(Config.Libs.AndroidX.lifeCycleViewModel)
    implementation(Config.Libs.AndroidX.navigationFragment)
    implementation(Config.Libs.AndroidX.navigationUI)
    implementation(Config.Libs.AndroidX.recyclerView)
    implementation(Config.Libs.AndroidX.legacySupport)

    implementation(Config.Libs.DI.dagger)
    kapt(Config.Libs.DI.daggerCompiler)
    compileOnly(Config.Libs.DI.glassFish)

    implementation(Config.Libs.Network.retrofit)
    implementation(Config.Libs.Network.gsonConverter)
    implementation(Config.Libs.Network.retrofitMock)
    implementation(Config.Libs.Network.loggingInterceptor)

    implementation(Config.Libs.ExoPlayer.core)
    implementation(Config.Libs.ExoPlayer.ui)

    implementation(Config.Libs.Room.runtime)
    implementation(Config.Libs.Room.extensions)
    kapt(Config.Libs.Room.compiler)

    implementation(Config.Libs.Misc.timber)
    implementation(Config.Libs.Misc.coil)

    testImplementation(Config.Libs.Test.jUnit)
    testImplementation(Config.Libs.Test.mockitoKt)
    testImplementation(Config.Libs.Test.testingCore)
    testImplementation(Config.Libs.Test.mockitoInline)
    testImplementation(Config.Libs.Test.coroutines)

    androidTestImplementation(Config.Libs.Test.androidJUnit)
    androidTestImplementation(Config.Libs.Test.espresso)
}

apply(from = "../spotless.gradle")
