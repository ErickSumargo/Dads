[<img src="assets/logo.png" width="120" height="120">]()
[<img align="right" src="assets/playstore.png" width="200" height="80">](https://play.google.com/store/apps/details?id=com.bael.dads)

[![CI](https://github.com/ErickSumargo/Dads/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/ErickSumargo/Dads/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/ErickSumargo/Dads/branch/main/graph/badge.svg?token=T2CU8YFDCO)](https://codecov.io/gh/ErickSumargo/Dads)
[![Fossa Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FErickSumargo%2FDads.svg?type=small)](https://app.fossa.com/projects/git%2Bgithub.com%2FErickSumargo%2FDads/refs/branch/main)
[![Android Weekly](https://androidweekly.net/issues/issue-462/badge)](https://androidweekly.net/issues/issue-462)

**Just an app with lame dad jokes content to fill up your day.**

## MVP
[<img align="right" src="assets/demo.gif" width="40%">]()
**This MVP version features:**
- Feed walks you through the latest dad jokes,
- Browse back seen jokes & share your favorite ones,
- Notification to remind you up with latest available jokes,
- Light/ Dark theme based on preference.

## Stacks
#### Foundation
- [App Startup](https://developer.android.com/topic/libraries/app-startup) - Orchestrating single [ContentProvider](https://developer.android.com/reference/android/content/ContentProvider) at startup.
- [Coroutines](https://developer.android.com/kotlin/coroutines) - Performing asynchronous code with sequential manner.
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - The DI framework w/ Jetpack integration.
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Preferences in nutshell but with steroid.
- [Flow](https://developer.android.com/kotlin/flow) - Reactive streams based on coroutines, just like Rx. But simpler.
- [Fragment](https://developer.android.com/guide/fragments) - The UI host.
- [JavaPoet](https://github.com/square/javapoet) - Personal diff-state engine printer.
- [Lifecycle](https://developer.android.com/topic/libraries/architecture/coroutines) - Coroutines teams up with Android's component lifecycle.
- [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started) - The key player for adopting single-activity architecture with ease.
- [Room](https://developer.android.com/training/data-storage/room) - ORM for SQLite database. Also, try out its integration with [Database Inspector](https://developer.android.com/studio/inspect/database).   
- [View Binding](https://developer.android.com/topic/libraries/view-binding) - Providing safe access to view. 
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Presenter with its semi data persistence behavior.
- [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - Background job scheduler. You should also try out its integration with [WorkManager Inspector](https://developer.android.com/studio/preview/features#workmanager-inspector).

#### UI
- [BottomSheetDialogFragment](https://developer.android.com/reference/com/google/android/material/bottomsheet/BottomSheetDialogFragment) - As the name suggests.
- [ConcatAdapter](https://developer.android.com/reference/androidx/recyclerview/widget/ConcatAdapter) - Helping me group `ViewHolder` based on types in isolation.
- [ConstraintLayout](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout) - Helping me manage positioning & alignment with ease. 
- [Lottie](https://airbnb.design/lottie) - Providing animation asset.
- [Material Components](https://github.com/material-components/material-components-android) - Helping me present Material Design.
- [MotionLayout](https://developer.android.com/reference/androidx/constraintlayout/motion/widget/MotionLayout) - Animating view has never been easier.
- [ViewPager2](https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2) - Personal option over `RecyclerView` when dealing view snapping experience.

#### Internal
- [LiveListAdapter](libs/lib_presentation/src/main/kotlin/com/bael/dads/lib/presentation/widget/recyclerview/adapter/LiveListAdapter.kt) <br/>
ListAdapter w/ every visible cell (`ViewHolder`) is reactive. Cell acts like observer of data they hold, so it will auto-refresh if their related data is updated.<br/>
- [RenderExecutor Processor](processor/src/main/kotlin/com/bael/dads/processor) <br/>
Processor for `RenderWith` annotation. Diff-state engine generator for rendering view component.

#### Remote
- [Apollo GraphQL](https://www.apollographql.com) - Client for requesting GraphQL API.
- [OkHttp](https://square.github.io/okhttp) - Request interceptors. Pipeline before remote API call.

#### Testing
- [Espresso](https://developer.android.com/training/testing/espresso) - Android UI tests.
- [Hilt Testing](https://developer.android.com/training/dependency-injection/hilt-testing) - For swapping production dependencies with test doubles. 
- [Truth](https://truth.dev) - Assertion framework in tests by Google.

#### Health
* [Firebase Crashlytics](https://firebase.google.com/docs/crashlytics) - Collecting crash report for production environment. 
* [LeakCanary](https://square.github.io/leakcanary) (Debug) - Memory leak detector.
* [StrictMode](https://developer.android.com/reference/android/os/StrictMode) (Debug) - Tool for checking if any *should-be-background* operation is done on main thread.
<br/>

## MAD Scorecard
[<img src="assets/mad_scorecard.png">](https://madscorecard.withgoogle.com/scorecards/966921635/)
<br/>
<br/>

## Architecture
Dads adopts [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) with [Unidirectional flow (UDF)](https://en.wikipedia.org/wiki/Unidirectional_Data_Flow_(computer_science)) pattern.
<br/>
<br/>
Moreover, view components are rendered based on their related changed states only performed by diff-state engine, so the render operation is done more **efficiently**.
<br/>
<br/>
[<img src="assets/architecture.png">]()

## Design Principles
- [Elegant Objects (EO)](https://www.elegantobjects.org/).
- [O.L.I.D](https://en.wikipedia.org/wiki/SOLID), where's the [S?](https://www.yegor256.com/2017/12/19/srp-is-hoax.html)
- [The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).
- [Cursor-based pagination](https://uxdesign.cc/why-facebook-says-cursor-pagination-is-the-greatest-d6b98d86b6c0?gi=dd3f1a307f7e) over offset-based.

## Testing Principles
- Black-box/ behavior-driven testing with `UI` as the sole SUT (very recommended [read](https://dagger.dev/hilt/testing-philosophy.html) and [talk](https://www.youtube.com/watch?v=EZ05e7EMOLM)).
- No mocking framework! Only fake type as test double, if required.
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
    * Put the `schema.json` in directory: `libs/lib_remote/src/main/graphql/com/bael/dads/lib/remote/`,
    * Or you can run this command as alternative.
        ```
        ./gradlew downloadApolloSchema --endpoint="https://dads-engine.herokuapp.com" --schema="libs/lib_remote/src/main/graphql/com/bael/dads/lib/remote/schema.json"
        ```
* Set `JWT` key in `keys.properties` file (located in project root folder):
```
JWT=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHAiOiJEYWRzISIsInR5cGUiOiJVc2VyIn0.46oGvluHExsPIOS9d925RNYrk_Y9eke0Zm45ZbqjaXs
```
* Have fun!

## Caveat
After went through **How to run** section but you still get a "Service issue" state, hit `TRY AGAIN?` a couple of times until success. That's because I host data to the server with basic free plan - potato spec, well, it's just for research purpose anyway 😂. 

## Contributing
[Let's get in touch](https://twitter.com/SumargoErick) if you're interested in contributing. Fork it, submit your PR.<br/>
Also feel free to open new issue, request features, or any kind of your support (join [**stargazers** ⭐](https://github.com/ErickSumargo/Dads/stargazers)️ | treat me [**coffee**](https://www.buymeacoffee.com/ErickSumargo) 😁).

Those wonder what's next, check out my planned Roadmap by [**GitHub project board**](https://github.com/ErickSumargo/Dads/projects/1). 

## Licenses
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FErickSumargo%2FDads.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2FErickSumargo%2FDads?ref=badge_large)