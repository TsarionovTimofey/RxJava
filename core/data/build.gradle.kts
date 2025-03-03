plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "tsarionovtimofey.tests.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

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
    implementation(project(":core:domain"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.1.1")

    implementation("com.google.dagger:dagger:2.55")
    kapt("com.google.dagger:dagger-compiler:2.55")
}