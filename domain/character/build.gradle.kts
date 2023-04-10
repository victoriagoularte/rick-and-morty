plugins {
    id("com.viclab.library")
    id("com.viclab.hilt")
}

android {
    namespace = "com.viclab.domain"
}

dependencies {
    implementation(project(":model"))
    implementation(project(":data:character"))

    testImplementation(libs.bundles.test)
}