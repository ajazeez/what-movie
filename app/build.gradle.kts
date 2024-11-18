import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
    id ("com.google.dagger.hilt.android")
}


val localPropertiesFile = rootProject.file("local.properties")
val localProperties = Properties()
localProperties.load(FileInputStream(localPropertiesFile))

android {
    namespace = "com.aj.whatmovie"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.aj.whatmovie"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField( "String", "API_KEY", localProperties["API_KEY"].toString())
        buildConfigField("String", "BASE_URL", localProperties["BASE_URL"].toString())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.multidex)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    // For instrumentation tests
    androidTestImplementation  (libs.hilt.android.testing)
    kaptAndroidTest (libs.hilt.compiler)

    // For local unit tests
    testImplementation (libs.hilt.android.testing)
    kaptTest (libs.hilt.compiler)

    implementation (libs.androidx.navigation.compose)
    implementation (libs.androidx.hilt.navigation.compose)

    implementation ( libs.androidx.paging.runtime.ktx)
    implementation (libs.androidx.paging.compose)

    implementation (libs.timber)

    //retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.logging.interceptor)

    //coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    implementation (libs.androidx.constraintlayout.compose)

    //Lottie
    implementation(libs.dotlottie.android)

    implementation(libs.androidx.core.splashscreen)


}