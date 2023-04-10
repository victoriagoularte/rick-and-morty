plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.viclab.ricknmorty"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.viclab.ricknmorty"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    kapt {
        correctErrorTypes = true
    }

    packagingOptions {
        packagingOptions.resources.excludes += setOf(
            "META-INF/*.version",
            "META-INF/gradle/*",
            "META-INF/gradle/*.processors",
            "META-INF/proguard/*",
            "/*.properties",
            "fabric/*.properties",
            "META-INF/*",
        )
    }
}

dependencies {
    implementation(project(":ui"))
//    implementation(project(":model"))
    implementation(project(":features:characters"))

    kapt(libs.hilt.compiler)
    implementation(libs.bundles.hilt)
    implementation(libs.bundles.compose)

    with(libs.androidx) {
        implementation(appcompat)
        implementation(constraintlayout)
        implementation(core.ktx)
        implementation(work.runtime)
    }

    testImplementation(libs.bundles.test)
}