object Config {
    object App {
        object SdkVersions {
            const val min = 21
            const val compile = 30
            const val target = 30
        }

        object Versions {
            const val code = 1
            const val name = "1.0.0"
            const val buildTools = "30.0.2"
        }

        object Variables {
            const val appID = "com.efedaniel.ulesson"
            const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    object Versions {
        const val kotlin = "1.4.10"
        const val gradle = "4.1.1"
        const val androidXCore = "1.3.2"
        const val legacySupport = "1.0.0"
        const val appCompat = "1.2.0"
        const val material = "1.2.0"
        const val constraintLayout = "2.0.4"
        const val jUnit = "4.+"
        const val androidJUnit = "1.1.2"
        const val espresso = "3.3.0"
        const val dagger = "2.31.1"
        const val glassFish = "10.0-b28"
        const val timber = "4.7.1"
        const val retrofit = "2.9.0"
        const val okHttp = "4.9.0"
        const val coroutines = "1.3.9"
        const val lifecycle = "2.2.0"
        const val navigation = "2.3.2"
        const val recyclerView = "1.1.0"
        const val coil = "1.1.1"
        const val exoPlayer = "2.12.0"
        const val room = "2.2.6"
        const val mockito = "2.2.0"
        const val mockitoInline = "2.13.0"
        const val testingCore = "2.1.0"
        const val spotless = "5.7.0"
    }

    object Plugins {
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
        const val spotless = "com.diffplug.spotless:spotless-plugin-gradle:${Versions.spotless}"
    }

    object Libs {
        object Core {
            const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
            const val material = "com.google.android.material:material:${Versions.material}"
            const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
            const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        }

        object AndroidX {
            const val coreKtx = "androidx.core:core-ktx:${Versions.androidXCore}"
            const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
            const val lifeCycleExt = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
            const val lifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
            const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
            const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
            const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
            const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
        }

        object DI {
            const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
            const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
            const val glassFish = "org.glassfish:javax.annotation:${Versions.glassFish}"
        }

        object Network {
            const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
            const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
            const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
            const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        }

        object ExoPlayer {
            const val core = "com.google.android.exoplayer:exoplayer-core:${Versions.exoPlayer}"
            const val ui = "com.google.android.exoplayer:exoplayer-ui:${Versions.exoPlayer}"
        }

        object Room {
            const val runtime = "androidx.room:room-runtime:${Versions.room}"
            const val extensions = "androidx.room:room-ktx:${Versions.room}"
            const val compiler = "androidx.room:room-compiler:${Versions.room}"
        }

        object Misc {
            const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
            const val coil = "io.coil-kt:coil:${Versions.coil}"
        }

        object Test {
            const val jUnit = "junit:junit:${Versions.jUnit}"
            const val mockitoKt = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito}"
            const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
            const val androidJUnit = "androidx.test.ext:junit:${Versions.androidJUnit}"
            const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
            const val testingCore = "androidx.arch.core:core-testing:${Versions.testingCore}"
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        }

    }
}


