@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.example.module_test_compose"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.8"
        }
    }
}

dependencies {

//    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
//    implementation(libs.android.material)
//    testImplementation(libs.junit.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso)
//
//    implementation(libs.compose.ui)
//    implementation(libs.compose.activity)
//    implementation(libs.compose.material3)
//    implementation(libs.compose.uitoolingpreview)
//    debugImplementation(libs.compose.uitooling)
//    implementation(libs.compose.uitest)

//    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation(libs.androidx.ktx)
    implementation(libs.compose.activity)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.uitooling)
    implementation(libs.compose.uitoolingpreview)

    testImplementation(libs.junit.junit)

    //androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(libs.compose.uitest)
    androidTestImplementation(libs.androidx.espresso)
    androidTestImplementation(libs.androidx.junit)

    debugImplementation(libs.compose.ui.test.manifest)
}