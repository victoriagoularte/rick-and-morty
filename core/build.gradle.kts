plugins {
    id("com.viclab.library")
    id("com.viclab.hilt")
    id("com.viclab.compose")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.viclab.core"
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    implementation(libs.bundles.network)
    implementation(libs.mockwebserver)

    with(libs.androidx) {
        implementation(core.ktx)
        implementation(lifecycle.viewmodel)
        implementation(lifecycle.viewmodel.ktx)
        implementation(test.runner)
    }

    implementation(libs.coroutinesTest)
}