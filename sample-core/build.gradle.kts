plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.architecture.sample_core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // android core dependency
    implementation(libs.core.ktx)
    implementation(libs.activity)

    // kotlin collection
    implementation(libs.kotlin.collection)

    // compose dependency
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    // hilt dependency
    ksp(libs.hilt.android.compiler)
    implementation(libs.bundles.hilt)

    // room database dependency
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // retrofit dependency for network calls
    implementation(libs.bundles.retrofit)

    // testing dependency
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
}