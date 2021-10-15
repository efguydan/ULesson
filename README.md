# iStudy
![Android CI](https://github.com/efguydan/iStudy/workflows/Android%20CI/badge.svg)
[![Download Sample](https://img.shields.io/badge/Download-v1.0.0-blue.svg)](https://github.com/efguydan/iStudy/raw/master/showcase/iStudy-1.0.0.apk)

iStudy is an offline first Android application to watch videos about different educational topics.

## Tech Stack & Open-source libraries
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct database.
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

- MVVM is structured to separate program logic and the user interface. The View contains the User interface, the Model contains the data while the Viewmodel connects them together, providing transformational logic where necessary.
- The ViewModel survives configuration changes and provides a safe space to save data used by the fragment or activity. This was particularly used in the lesson fragment where the exoplayer instance is hosted in the viewmodel in order to survive configuration changes and keeps a smooth streaming experience.
- The ViewModel, out of the box supports Reactive Programming. The ViewModel doesn't have to be aware of the View. It only exposes observables which the view can subscribe to and transform into UI for the user. This leads to lesser coupling of components.

The application consists of a single activity housing multiple fragments with navigation component used to define destinations and actions. Navigation Component also provides safe args which eases passing data into fragments in a null-safe way. 

Dagger was also used for providing dependencies for different components in the project. This helps greatly in managing and scoping dependencies used by different components in the project.

To provide offline first functionality in the project, Room Database was used. Every Viewmodel in the project gets its data from the app's database first, before proceeding to make an API call to update the data in that database. Room's ability to provide its data in LiveData format was utilized as changes in the db made by the results from the API call will automatically reflect in the the viewmodel.

View Binding and Data Binding were used in the project to provide null safe view referencing to the layout views. View Binding for that sole purpose, Data Binding to bind UI components in the layouts to data sources in the viewmodel. This removes some unnecessary concern from the fragment / activity and moves those over to the xml itself. The binding is in turn scoped to the lifecycle of the fragment / activity to prevent any leaks.


## Code Formatting

Code Formatting is done with the gradle spotless plugin, using [ktlint](https://github.com/pinterest/ktlint) as the linter. Other settings for the plugin can be configured in [spotless.gradle](spotless.gradle). To format code, run

```gradle
./gradlew spotlessApply
```

