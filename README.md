# Template Android

This project is a template for an android project that used the following libraries 👇

- Navigation (Fragment transitions)
- View Binding (Bind views)
- ViewModel (Store and manage UI-related data)
- LveData (Observable data)
- Kotlin Coroutine (Light-weight threads)
- Dagger2 (Dependency Injection)
- Hilt (Dependency Injection for Android)
- Retrofit (HTTP client)
- Multi Module App
- Mockito
- Compose
- Room
- KotlinDsl

It is also configured to work with compose and dynamic modules

## Refactoring

In order to change the versions and dependecies access the buildSrc module. It contains everything related to versions and packages names

## Modules

The project contains the following base modules:

- App ( Initial module with the main activity of the project. Because single activity was used on the project )
- Base ( Contains the navigation between the UI modules and generic classes )
- Data ( Used for making request to the API and the the local BD)
- Model ( Declares all the entities used in the project )
- ⚠ For the UI you are supposed to build new modules that can be dynamic or a feature module

## Architecture

This app was build using MVVM and following the guidelines explain [here](https://developer.android.com/jetpack/docs/guide). This app also makes use of the pattern single activity.

All the transitions for the app where made using [Navigation](https://developer.android.com/guide/navigation)

## Run the app locally
As the app makes usd of dynamic modules in order to test it locally you need to generate a universal version containing all the modules. The following commands allow to generate the apk 👇

```sh
//Windows
gradlew :app:packageDebugUniversalApk
//Mac/linux
./gradlew :app:packageDebugUniversalApk
```

## 🚨 Dependency Issues

List of issue related to version of dependecies and limitations

- Compose navigation dont not allow to use dynamic modules natively- Google issue [here](https://issuetracker.google.com/issues/183677219)

## Sources

- [Jet Hub](https://github.com/TakuSemba/JetHub) for module and di management
- [kmm template](https://github.com/jittya/KMMT) for buildSrc handling
- [libraries](https://proandroiddev.com/avoid-repetitive-dependency-declarations-with-gradle-kotlin-dsl-97c904704727) for sharing dependencies between modules

## Author

* **Renato Mercado**
    * **Github** - (https://github.com/merRen22)
    * **Twitter** - (https://twitter.com/renato_ml22)
    * **LinkedIn** - (www.linkedin.com/in/renato-mercado-luna22)

## Licence
```
Copyright 2021 Renato Mercado.

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
