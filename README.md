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

It is also configured to work with compose and dynamic modules

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