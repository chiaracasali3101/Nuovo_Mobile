plugins {
    alias(libs.plugins.android.library)
}


android {
    namespace = "com.unibo.android.data"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }


    defaultConfig {
        minSdk = 31
        @@ -27, 8+21, 18 @@ kotlin {
    }

        dependencies {
            implementation(project(":domain"))

            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.room.ktx)
            kapt(libs.androidx.room.compiler)
            implementation("com.google.code.gson:gson:2.10.1")
            implementation("com.squareup.retrofit2:retrofit:2.9.0")
            implementation("com.squareup.retrofit2:converter-gson:2.9.0")
            implementation(project(":domain"))

            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.core)
            implementation(libs.material)
            testImplementation(libs.junit)
            androidTestImplementation(libs.androidx.junit)
            androidTestImplementation(libs.androidx.espresso.core)
        }