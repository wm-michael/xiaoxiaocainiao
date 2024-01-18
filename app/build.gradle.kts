plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "im.wangyan.xiaoxiaocainiao"
    compileSdk = 34

    resourcePrefix = "app_"

    defaultConfig {
        applicationId = "im.wangyan.xiaoxiaocainiao"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isDebuggable =  true
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
}

dependencies {

    implementation(project(":module-retrofit"))
    implementation(project(":module-room"))
    implementation(project(":module-ui-demo"))
    implementation(project(":module-apollographql"))
    implementation(project(":module-glide"))
    implementation(project(":module-localstore"))
    implementation(project(":module-xlog"))
    implementation(project(":module-workmanager"))
    implementation(project(":module-exoplayer"))
    implementation(project(":module-compose"))
    implementation(project(":module-test-compose"))
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}