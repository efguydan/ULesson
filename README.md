# iStudy
[![Android CI](https://github.com/efguydan/ULesson/actions/workflows/android.yml/badge.svg)](https://github.com/efguydan/ULesson/actions/workflows/android.yml)
[![Download Sample](https://img.shields.io/badge/Download-v1.0.0-blue.svg)](https://github.com/efguydan/iStudy/raw/master/showcase/ULesson-1.0.0.apk)

ULesson is an app to view a list of live lessons

## Tech Stack & Open-source libraries
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous
- JetPack
  - LiveData - notify domain layer data to views.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - database.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
  - Dagger 2 - Dependency Injection
- [ExoPlayer](https://github.com/google/ExoPlayer) - playing video
- [Retrofit2 & Gson](https://github.com/square/retrofit) - constructing the REST API
- [OkHttp3](https://github.com/square/okhttp) - implementing interceptor and logging
- [Coil](https://github.com/coil-kt/coil) - loading images
- [Timber](https://github.com/JakeWharton/timber) - logging
- [Mockito](https://github.com/mockito/mockito-kotlin) - For mocking dependencies behaviours during Unit Tests

## System Architecture

The Architecture used in the project is MVVM. Some reasons it was used include:

  - MVVM does a very good job in separating concern. Data and User Interface know nothing about one another. The ViewModel handles all the logic and also 
    knows nothing about the view. This eases the process of writing Unit Tests.
  - The ViewModel(used in implementing the MVVM architecture) is lifecycle-aware. This means that it disposes of resources as soon as the fragment hosting 
    it is destroyed. This prevents Memory Leaks. It also survives configuration changes. Therefore user can have access to their current state when they undergo
    a configuration change; e.g. switching from portrait to landscape.
    
The application consists of a single activity hosting multiple fragments with navigation component used to define destinations and actions.

For Dependency Injection, Dagger 2 was used. Dagger2 greatly helps in managing and scoping dependencies used across different components in the project. 

To cache "My Lessons" Data, Room Persistence was used. Tables are created to save my lessons in the database and this is connected to the view to show to the user.
When the user is offline, existing data in this table is displayed to the user, providing offline first functionality.

View Binding and Data Binding were used in the project to provide null safe view referencing to the layout views. View Binding for that sole purpose, Data Binding
to bind UI components in the layouts to data sources in the viewmodel. This removes some unnecessary concern from the fragment / activity and moves those over to
the xml itself. The binding is in turn scoped to the lifecycle of the fragment / activity to prevent any memory leaks.

## Code Formatting

Code Formatting is done with the gradle spotless plugin, using [ktlint](https://github.com/pinterest/ktlint) as the linter. Other settings for the plugin can be configured in [spotless.gradle](spotless.gradle). To format code, run

```gradle
./gradlew spotlessApply
```

