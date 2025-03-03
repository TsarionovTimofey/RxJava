plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "tsarionovtimofey.animations.rxjava"
    compileSdk = 35

    defaultConfig {
        applicationId = "tsarionovtimofey.animations.rxjava"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":core:data"))
    implementation(project(":feature:login"))
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(project(":feature:dashboard"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.1.1")

    implementation("com.google.dagger:dagger:2.55")
    kapt("com.google.dagger:dagger-compiler:2.55")
    implementation("androidx.activity:activity-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
}