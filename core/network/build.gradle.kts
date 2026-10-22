import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

val secretsFile = rootProject.file("secrets.properties")
val secretsProps = Properties()

if (secretsFile.exists()) {
    secretsFile.inputStream().use { secretsProps.load(it) }
}

val apiBaseUrl = secretsProps.getProperty("API_BASE_URL") ?: ""
val authBaseUrl = secretsProps.getProperty("AUTH_BASE_URL") ?: ""
val authApiKey = secretsProps.getProperty("AUTH_API_KEY") ?: ""

android {
    namespace = "com.example.network"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField(
            "String",
            "API_BASE_URL",
            "\"$apiBaseUrl\""
        )

        buildConfigField(
            "String",
            "AUTH_API_KEY",
            "\"$authApiKey\""
        )

        buildConfigField(
            "String",
            "AUTH_BASE_URL",
            "\"$authBaseUrl\""
        )
    }

    buildFeatures {
        buildConfig = true
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
}

dependencies {

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi)
    implementation(libs.converter.moshi)
    implementation(libs.logging.interceptor)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}