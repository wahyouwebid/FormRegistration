# Simple Form Registration with Clean Architectures
Native Kotlin

## Screenshot App
#### Data Diri
<table>
  <tr>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688791903/screeenshot/1.png" alt="Screenshot 1" width="200">
    </td>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688791903/screeenshot/2.png" alt="Screenshot 2" width="200">
    </td>
   <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688791903/screeenshot/3.png" alt="Screenshot 3" width="200">
    </td>
   <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688791903/screeenshot/4.png" alt="Screenshot 4" width="200">
    </td>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688791903/screeenshot/6.png" alt="Screenshot 5" width="200">
    </td>
  </tr>
</table>

#### Alamat KTP & Preview
<table>
  <tr>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688791903/screeenshot/7.png" alt="Screenshot 6" width="200">
    </td>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688791903/screeenshot/8.png" alt="Screenshot 7" width="200">
    </td>
   <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688791903/screeenshot/9.png" alt="Screenshot 8" width="200">
    </td>
   <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688791903/screeenshot/10.png" alt="Screenshot 9" width="200">
    </td>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688791903/screeenshot/11.png" alt="Screenshot 10" width="200">
    </td>
  </tr>
</table> 

<br>

## Setup Project
For Gradle, i am using the Kotlin DSL, so the extension of the build.gradle file, which was previously in Groovy, will be changed to *build.gradle.kts*, as shown in the following image.

<table>
  <tr>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688793875/Screenshot_2023-07-08_at_12.24.02_1.png" alt="Screenshot 1" width="100%">
    </td>
  </tr>
</table>

Please used JAVA Version using VERSION_17:

```kotlin
android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
```

Android Gradle Plugin Version **8.0.1** and Gradle Version **8.0**

```kotlin
plugins {
    id("com.android.application") version "8.0.1" apply false
    id ("com.android.library") version "8.0.1" apply false
    id ("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id ("com.google.dagger.hilt.android") version "2.44" apply false
}
```

<br>

## Architectures
The architecture of this project, I am using **Clean Architecture** combined with **Model View ViewModel (MVVM)**, which acts as the **presentation layer**.

Here is the implemented flow diagram for this project:
<table>
  <tr>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688796402/screeenshot/Screenshot_2023-07-08_at_13.06.23.png" alt="Screenshot 1" width="100%">
    </td>
  </tr>
</table>

Here is the project structure that I have implemented using **Clean Architecture**.

<table>
  <tr>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688795071/screeenshot/Screenshot_2023-07-08_at_12.42.44.png" alt="Screenshot 1" width="100%">
    </td>
   <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688795070/screeenshot/Screenshot_2023-07-08_at_12.42.57.png" alt="Screenshot 2" width="100%">
    </td>
   <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688795072/screeenshot/Screenshot_2023-07-08_at_12.44.01.png" alt="Screenshot 3" width="100%">
    </td>
  </tr>
</table>

<br>

## Api Province

The API used in this project is https://app.goapi.id or https://goapi.id/.

The base URL used is as follows:
```
https://api.goapi.id/v1/
```

|Method | Endpoint | Usage |
| ---- | ---- | --------------- |
|GET| `regional/provinsi?api_key{API_KEY}` or `regional/provinsi?api_key=Mz4GqJZQbtqFh7o3UVTaEvUQ3qpX7q` | Get All Province|

<br>

## 3rd Party Library

I am using several third-party libraries for this project, such as libraries for Dependency Injection, RestAPI, Reactive Programming, Unit Testing, etc.

### Dependency Injection
|Name | Library |
| ---- | ---- |
|Dagger Hilt| com.google.dagger:hilt-android:2.44 |
|Dagger Hilt Compiler| com.google.dagger:hilt-android-compiler:2.44 | 
|AndroidX Hilt Compiler| androidx.hilt:hilt-compiler:1.0.0|

### Reactive Programming & Data Manipulation
|Name | Library |
| ---- | ---- |
|RxAndroid| io.reactivex.rxjava3:rxandroid:3.0.0 |
|RxRava| io.reactivex.rxjava3:rxjava:3.0.7 |
|RxBinding| com.jakewharton.rxbinding3:rxbinding:3.0.0|

### Networking & Rest Api
|Name | Library |
| ---- | ---- |
|Retrofit2| com.squareup.retrofit2:retrofit:2.9.0 |
|Retrofit2 RxJava| com.squareup.retrofit2:adapter-rxjava3:2.9.0 |
|Retrofit2 Gson| com.squareup.retrofit2:converter-gson:2.9.0|

### Interceptor & Logging
|Name | Library |
| ---- | ---- |
|OkHttp| com.squareup.okhttp3:okhttp:4.9.0 |
|OkHttp Interceptor| com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3 |
|Chucker Debug| com.github.chuckerteam.chucker:library:3.5.2 |
|Chucker Release| com.github.chuckerteam.chucker:library-no-op:3.5.2 |

### Unit Test
|Name | Library |
| ---- | ---- |
|Junit4| junit:junit:4.13.2 |
|Mockito| org.mockito:mockito-core:4.0.0 |

<br>

## Unit Test

Here are the results of the conducted unit tests

<table>
  <tr>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1688798141/screeenshot/Screenshot_2023-07-08_at_13.35.22.png" alt="Screenshot 1" width="100%">
    </td>
  </tr>
</table>

<br>

<br>
 <h1>Creator</h1>
 <p>created by [wahyouwebid](http://github.com/wahyouwebid)</p>
 <p>You can contact me at : wahyouwebid@gmail.com</p>

