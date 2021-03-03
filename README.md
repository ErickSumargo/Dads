<p align="center" style="margin: 0;">
    <img src="assets/logo.png" width="120" height="120">
</p>

<h4 align="center" style="margin: 0;">
    *BA DUM TSSS*
</h4>

<p align="center" style="margin: 16px;">
    <a href="https://app.fossa.com/projects/git%2Bgithub.com%2FErickSumargo%2FDads?ref=badge_small">
        <img src="https://app.fossa.com/api/projects/git%2Bgithub.com%2FErickSumargo%2FDads.svg?type=small" />
    </a>
</p>

<p align="center">
    Just an app with lame dad jokes content to fill up your day.
</p>

## MVP
<div style="display: flex;">
    <video width="360" height="420" controls>
      <source src="assets/demo.mp4" type="video/mp4">
    </video>
    <div>
        <p><b>This MVP version features:</b></p>
        <ul>
            <li>Feed walks you through the latest dad jokes,</li>
            <li>Browse back seen jokes & share your favorite ones,</li>
            <li>Notification to remind you up with latest available jokes,</li>
            <li>Light/Dark theme based on preference.</li>
        </ul>
    </div>
</div>

## Architecture
Dads adopts [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) with [Unidirectional flow (UDF)](https://en.wikipedia.org/wiki/Unidirectional_Data_Flow_(computer_science)) pattern.
<br/>
<br/>
Moreover, view components are rendered based on their related changed states only performed by diff-state engine, so the render operation is done more **efficiently**.
[<img src="assets/architecture.png">]()

## Design Principles
- [Elegant Objects (EO)](https://www.elegantobjects.org/),
- [S.O.L.I.D](https://en.wikipedia.org/wiki/SOLID),
- [The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html),
- Behavior testing driven with `ViewModel` as the sole SUT [(Very recommended talk)](https://www.youtube.com/watch?v=EZ05e7EMOLM).

## Stacks
#### Foundation
- [App Startup](https://developer.android.com/topic/libraries/app-startup) - Preventing external dependency initializing w/ their own content provider creation.
- [Coroutines](https://developer.android.com/kotlin/coroutines) - Performing asynchronous code with sequential manner. 
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - The DI framework w/ Jetpack integration.
- [Flow](https://developer.android.com/kotlin/flow) - Reactive streams based on coroutines, just like Rx. But simpler.
- [Fragment](https://developer.android.com/guide/fragments) - The UI screen.
- [JavaPoet](https://github.com/square/javapoet) - Personal diff-state engine printer.
- [Lifecycle](https://developer.android.com/topic/libraries/architecture/coroutines) - Coroutines respects over Android's component lifecycle.
- [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started) - The key player for adopting single-activity architecture with ease.
- [Room](https://developer.android.com/training/data-storage/room) - ORM for SQLite database. Also, try out its integration with [Database Inspector](https://developer.android.com/studio/inspect/database).   
- [View Binding](https://developer.android.com/topic/libraries/view-binding) - Providing safe access to view. 
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - The presenter with its semi data persistence behavior.
- [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - Background job scheduler. You should also try out its integration with [WorkManager Inspector](https://developer.android.com/studio/preview/features#workmanager-inspector).

#### UI
- [BottomSheetDialogFragment](https://developer.android.com/reference/com/google/android/material/bottomsheet/BottomSheetDialogFragment) - As the name suggests.
- [ConcatAdapter](https://developer.android.com/reference/androidx/recyclerview/widget/ConcatAdapter) - Helping me group `ViewHolder` based on types.
- [ConstraintLayout](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout) - Helping me manage positioning & alignment with ease. 
- [Lottie](https://airbnb.design/lottie) - Providing animation asset.
- [Material Components](https://github.com/material-components/material-components-android) - Helping me present Material Design.
- [MotionLayout](https://developer.android.com/reference/androidx/constraintlayout/motion/widget/MotionLayout) - Animating view has never been easier.
- [ViewPager2](https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2) - Personal option over `RecyclerView` when dealing view snapping experience.

#### Internal
- [LiveListAdapter](libs/lib_presentation/src/main/java/com/bael/dads/lib/presentation/widget/recyclerview/adapter/LiveListAdapter.kt) <br/>
ListAdapter w/ every visible cell (`ViewHolder`) is reactive. Cell acts like observer of data they hold, so it will auto-refresh if their related data is updated.<br/><br/>
- [RenderExecutor Processor](processor/src/main/java/com/bael/dads/processor) <br/>
Processor for `RenderWith` annotation. Diff-state engine generator for rendering view component.

#### Remote
- [Apollo GraphQL](https://www.apollographql.com) - Client for requesting GraphQL API.
- [OkHttp](https://square.github.io/okhttp) - Request interceptor. Pipeline before remote API call.

#### Testing
- [JUnit 5](https://junit.org/junit5) - Latest unit testing framework from JUnit family.
- [Truth](https://truth.dev) - Assertion framework in tests by Google.

#### Health
* [Firebase Crashlytics](https://firebase.google.com/docs/crashlytics) - Collecting crash report for production environment. 
* [LeakCanary](https://square.github.io/leakcanary) (Debug) - Memory leak detector.
* [StrictMode](https://developer.android.com/reference/android/os/StrictMode) (Debug) - Tool for checking if any *should-be-background* operation is done on main thread.
<br/>
<br/>

## MAD Scorecard
[<img src="assets/mad_scorecard.png">](https://madscorecard.withgoogle.com/scorecards/3887241481/)
<br/>
<br/>

[<img align="left" width="100" height="100" src="assets/graphql.png">]()
## GraphQL Engine
Jokes are requested from proprietary GraphQL service, the [Dads-Engine](https://github.com/ErickSumargo/Dads-Engine). Check it out 🔥
<br/>
<br/>

## How to run
* Since this project employs GraphQL stack, you need to download the [schema](https://www.apollographql.com/docs/tutorial/schema/) first:
    * Go to hosted [GraphQL Playground](https://dads-engine.herokuapp.com/graphql),
    * Open tab `SCHEMA` at the right side. `DOWNLOAD` it,
    * Put the `schema.json` in directory: `libs/lib_api/src/main/graphql/com/bael/dads/lib/api/`,
    * Or you can run this command as alternative.
        ```
        ./gradlew downloadApolloSchema --endpoint="https://dads-engine.herokuapp.com" --schema="libs/lib_api/src/main/graphql/com/bael/dads/lib/api/schema.json"
        ```
* Replace `$JWT` placeholder in `AuthInterceptor.kt` with the shared key (see **Contributing** section 👇).
* Have fun!

## Contributing
[Let's get in touch](https://twitter.com/SumargoErick) if you're interested in contributing. Fork it, submit your PR.<br/>
Also feel free to open new issue, feature request, or any kind of related support ❤️.

Those who ask what's next, check out my planned Roadmap by [GitHub project board](https://github.com/ErickSumargo/Dads/projects/1). 

## Licenses
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FErickSumargo%2FDads.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2FErickSumargo%2FDads?ref=badge_large)