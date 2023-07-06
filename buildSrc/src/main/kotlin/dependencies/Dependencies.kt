package dependencies

import versions.Versions

object Dependencies {

    /** Android Jetpack **/
    const val ANDROIDX_CORE = "androidx.core:core-ktx:${Versions.CORE}"
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val ANDROIDX_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val ANDROIDX_ACTIVITY_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
    const val ANDROIDX_FRAGENT_KTX = "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"

    /** Networking Retfotit & OkHttp **/
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_RX_JAVA = "com.squareup.retrofit2:adapter-rxjava3:${Versions.RETROFIT}"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.GSON_CONVERTER}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_INTERCEPTOR}"
    const val CHUCKER = "com.github.chuckerteam.chucker:library:${Versions.CHUCKER}"
    const val CHUCKER_NOOP = "com.github.chuckerteam.chucker:library-no-op:${Versions.CHUCKER}"

    /** Dagger Hilt **/
    const val HILT = "com.google.dagger:hilt-android:${Versions.DAGGER}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.DAGGER}"
    const val HILT_ANDROIDX_COMPILER = "androidx.hilt:hilt-compiler:${Versions.HILT}"

    /** UI Library **/
    const val MATERIAL = "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"

    /** RxJava **/
    const val RX_ANDROID = "io.reactivex.rxjava3:rxandroid:${Versions.RX_ANDROID}"
    const val RX_JAVA = "io.reactivex.rxjava3:rxjava:${Versions.RX_JAVA}"
    const val RX_BINDING = "com.jakewharton.rxbinding3:rxbinding:${Versions.RX_BINDING}"

    /** Testing **/
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_JUNIT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ANDROID_TEST_ESPRESSO}"
    const val JUNIT = "junit:junit:${Versions.TEST_JUNIT}"
}