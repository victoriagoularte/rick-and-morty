plugins {
    id("com.viclab.library")
    id("com.viclab.hilt")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.viclab.database.character"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":model"))

    with(libs.androidx) {
        implementation(core.ktx)
        implementation(paging.common)
    }

    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)

    testImplementation(libs.bundles.test)
}