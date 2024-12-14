import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

// local.properties 파일 불러오기
private val properties = Properties().apply {
    load(FileInputStream(rootProject.file("local.properties")))
}

// local.properties에 server_ip라는 키의 Value를 가지고 옮
val serverIP: String = properties.getProperty("server_ip")

android {
    namespace = "com.hangeulmansae.androidoriginmodule"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.hangeulmansae.androidoriginmodule"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Manifest에서 사용해야 할 경우 => Manifest에서는 ${SERVER_IP}로 접근할 수 있다.
        manifestPlaceholders["SERVER_IP"] = serverIP

        // Fragment 등 기타 파일에서 사용해야 할 경우
        buildConfigField("String", "SERVER_IP", serverIP)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // Fragment등에서 사용하기 위해 설정
    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    }
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.converter.scalars)
    implementation(libs.logging.interceptor)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}