import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementShared() {
  // desugar
  add("coreLibraryDesugaring", Dependencies.DeSugar.DeSugar)
  // kotlin
  add("implementation", Dependencies.Kotlin.kotlin)
  // coroutine
  add("implementation", Dependencies.Coroutine.coroutineCore)
  add("implementation", Dependencies.Coroutine.coroutineAndroid)
  add("implementation", Dependencies.Coroutine.coroutineTest)
  // dependency injection
  add("implementation", Dependencies.DependencyInjection.dagger)
  add("kapt", Dependencies.DependencyInjection.daggerCompiler)
  add("implementation", Dependencies.DependencyInjection.daggerHilt)
  add("kapt", Dependencies.DependencyInjection.daggerHiltCompiler)
  add("implementation", Dependencies.DependencyInjection.androidHilt)
  add("implementation", Dependencies.DependencyInjection.androidHiltViewModel)
  add("kapt", Dependencies.DependencyInjection.androidHiltCompiler)
}


fun DependencyHandler.implementCompose() {
  add("implementation", Dependencies.Compose.composeUi)
  add("implementation", Dependencies.Compose.composeUiTooling)
  add("implementation", Dependencies.Compose.composeCompiler)
  add("implementation", Dependencies.Compose.composeMaterial)
  add("implementation", Dependencies.Compose.composeFoundation)
  add("implementation", Dependencies.Compose.composeAnimation)
  add("implementation", Dependencies.Compose.composeLivedata)
  add("implementation", Dependencies.Image.coil)
}

fun DependencyHandler.implementTest() {
  add("testImplementation", Dependencies.Test.junit)
  add("testImplementation", Dependencies.Test.truth)
  add("testImplementation", Dependencies.Test.archCore)
  add("testImplementation", Dependencies.Test.mockk)
}

fun DependencyHandler.implementAndroidTest() {
  add("androidTestImplementation", Dependencies.Test.junit)
  add("androidTestImplementation", Dependencies.Test.truth)
  add("androidTestImplementation", Dependencies.AndroidTest.testCore)
  add("androidTestImplementation", Dependencies.AndroidTest.extJunitKtx)
  add("androidTestImplementation", Dependencies.AndroidTest.extTruth)
  add("androidTestImplementation", Dependencies.AndroidTest.espresso)
  add("androidTestImplementation", Dependencies.AndroidTest.composeTest)
  add("androidTestImplementation", Dependencies.DependencyInjection.daggerHiltTesting)
  add("androidTestImplementation", Dependencies.Test.archCore)
  add("kaptAndroidTest", Dependencies.DependencyInjection.daggerHiltCompiler)
}
