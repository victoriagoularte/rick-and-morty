plugins {
    id("com.viclab.library")
    id("com.viclab.hilt")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.viclab.data.character"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":model"))
    implementation(project(":database:character"))
    implementation(libs.bundles.network)

    with(libs.androidx) {
        implementation(core.ktx)
        implementation(paging.common)
    }

    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)

    testImplementation(libs.bundles.test)
}