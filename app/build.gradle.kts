import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.fulbiopretell.touristdirectory"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fulbiopretell.touristdirectory"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "MAPS_API_KEY", "\"\"")

    }

    flavorDimensions("version")

    productFlavors {
        create("development") {
            dimension = "version"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            val localProperties = Properties()
            val localPropertiesFile = rootProject.file("local.properties")
            if (localPropertiesFile.exists() && localPropertiesFile.isFile) {
                localProperties.load(localPropertiesFile.inputStream())
            }
            val mapsApiKey: String = localProperties.getProperty("DEV_MAPS_API_KEY", "")
            buildConfigField("String", "MAPS_API_KEY", "\"$mapsApiKey\"")
            manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey
        }
        create("staging") {
            dimension = "version"
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
            val localProperties = Properties()
            val localPropertiesFile = rootProject.file("local.properties")
            if (localPropertiesFile.exists() && localPropertiesFile.isFile) {
                localProperties.load(localPropertiesFile.inputStream())
            }
            val mapsApiKey: String = localProperties.getProperty("STAGING_MAPS_API_KEY", "")
            buildConfigField("String", "MAPS_API_KEY", "\"$mapsApiKey\"")
            manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey
        }
        create("production") {
            dimension = "version"
            val localProperties = Properties()
            val localPropertiesFile = rootProject.file("local.properties")
            if (localPropertiesFile.exists() && localPropertiesFile.isFile) {
                localProperties.load(localPropertiesFile.inputStream())
            }
            val mapsApiKey: String = localProperties.getProperty("PROD_MAPS_API_KEY", "")
            buildConfigField("String", "MAPS_API_KEY", "\"$mapsApiKey\"")
            manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit y OkHttp
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.logging)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Lifecycle y ViewModel
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    // Room
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)

    // Google Maps
    implementation(libs.maps)
    implementation(libs.location)

    // Glide
    implementation(libs.glide)
    kapt(libs.glide.compiler)

    // Navigation Components
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Keystore
    implementation(libs.key.store)
}

kapt {
    correctErrorTypes = true
}