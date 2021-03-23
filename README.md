⚠️ This library is fork from [Ashok-Varma/SharedPrefManager](github.com/Ashok-Varma/SharedPrefManager). Includes AndroidX and Kotlin artifacts.

# SharedPref Manager

**get sample apk from [Google Play Store][googlePlayStoreLink]**

<img src="https://raw.githubusercontent.com/Ashok-Varma/SharedPrefManager/master/sharedpref_320_5_compressed.gif" width="300" height="550" />

## What is this library about?
SharedPref Manager helps to manage your android Shared Preferences very effectively with ease

**(currently under active development, expect to see new releases almost daily)**

## Features

* Edit a SharedPreferences Item
* Add a SharedPreference Item
* Clear All SharedPreferences
* Delete a SharedPreference Item

## Download

You can implement library to your module level `build.gradle` file as shown below.

Gradle:

Groovy(`build.gradle`):
```groovy
debugImplementation 'com.github.bilgehankalkan:SharedPrefManager:1.2.2'
releaseImplementation 'com.github.bilgehankalkan:SharedPrefManager-no-op:1.2.2'
```

Kotlin(`build.gradle.kts`)
```kotlin
debugImplementation("com.github.bilgehankalkan:SharedPrefManager:1.2.2")
releaseImplementation("com.github.bilgehankalkan:SharedPrefManager-no-op:1.2.2")
```

## Usage

All the Shared-Pref Names to be should be sent to SharedPrefManager. It does remaining heavy lifting.

#### Sample Code
Java:
```java
SharedPrefManager
        .launchSharedPrefManager(
                context
                , new ArrayList<>(Arrays.asList(new String[]{"SHARED_PREF_1_PRIVATE", "SHARED_PREF_2_PRIVATE"}))// All your MODE_PRIVATE shared Shared Preference names, Null if None
                , new ArrayList<>(Arrays.asList(new String[]{"SP_WORLD_READ"}))//All your MODE_WORLD_READABLE Shared Preference Names, Null if None
                , new ArrayList<>(Arrays.asList(new String[]{"SP_WORLD_WRITE"}))//All your MODE_WORLD_READABLE Shared Preference Names, Null if None
         );
```
Kotlin:
```kotlin
  
SharedPrefManager.launchSharedPrefManager(
    context = this,
    privateSharedPrefNames = arrayListOf("PREFERENCE_NAME")
)
```
MODE_WORLD_READABLE, MODE_WORLD_READABLE are not supported by android system in Android N(Nougat) and above devices, If sent those will be ignored in Android N and above devices.

## License

```
SharedPref Manager library for Android
Copyright (c) 2016 Ashok Varma (http://ashokvarma.me/).

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
