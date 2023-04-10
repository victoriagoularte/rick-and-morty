plugins {
    id("com.viclab.feature")
}

android {
    namespace = "com.viclab.features.characters"

    packagingOptions {
        resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":model"))
    implementation(project(":domain:character"))

    with(libs.androidx) {
        implementation(core.ktx)
        implementation(paging.compose)
        implementation(paging.runtime)
        implementation(paging.common)
        implementation(work.runtime)
    }

    testImplementation(libs.bundles.test)

    androidTestImplementation(libs.bundles.androidTest)
}