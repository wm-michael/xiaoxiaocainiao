@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("com.apollographql.apollo3") version "3.8.2"
}


/** 必须有schema文件才可以通过gradle成功
 * ./gradlew downloadApolloSchema --endpoint='https://countries.trevorblades.com/graphql' --schema=lib-apollographql/src/main/graphql/com/example/schema.graphqls
 * --endpoint是schema文件下载路径
 * --schema是schema本地存放目录
 */
apollo {
    service("service") {
        packageName.set("com.example")
    }
}

android {
    namespace = "com.example.lib_apollographql"
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
}

dependencies {

    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)

    implementation(libs.apollo.graphql)
}