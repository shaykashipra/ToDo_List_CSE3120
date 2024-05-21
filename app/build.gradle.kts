plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
//    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

}

android {
    namespace = "com.example.todo_list"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.todo_list"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database:20.3.1")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//added
    implementation ("com.google.android.gms:play-services-maps:18.1.0")
    // Import the BoM for the Firebase platform
//    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))

    // dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")


    // the dependency for the Google Play services library and specify its version
    implementation("com.google.android.gms:play-services-auth:21.0.0")


// Use "+" to automatically fetch the latest version
//   implementation ("com.android.support:design:+") // Use "+" to automatically fetch the latest version
    implementation ("com.google.android.material:material:1.6.1")
    // JUnit for unit tests
    testImplementation ("junit:junit:4.13.2")
    // AndroidX Test-Instrumented testing
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test:core:1.4.0")

    androidTestImplementation ("androidx.fragment:fragment-testing:1.4.1")
    androidTestImplementation ("androidx.test:runner:1.4.0")
    androidTestImplementation ("androidx.test:rules:1.4.0")
    testImplementation ("org.robolectric:robolectric:4.6")

//    androidTestImplementation ("androidx.fragment:fragment-testing:1.3.6")
//    androidTestImplementation ("androidx.test:core:1.3.0")
//    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")


    // Espresso core
//  androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

    // Espresso intents
    androidTestImplementation ("androidx.test.espresso:espresso-intents:3.4.0")

    // JUnit
//  androidTestImplementation ("androidx.test.ext:junit:1.1.3")
//    


    // Unit testing dependencies

    testImplementation("org.mockito:mockito-core:3.+")
    androidTestImplementation("org.mockito:mockito-android:3.+")
//  androidTestImplementation ("org.robolectric:robolectric:4.6.1")

    // Mockito for Android
    androidTestImplementation ("org.mockito:mockito-android:3.11.2")
    androidTestImplementation ("org.mockito:mockito-android:3.3.3")





}