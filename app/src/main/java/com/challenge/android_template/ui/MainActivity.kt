package com.challenge.android_template.ui

import android.graphics.drawable.TransitionDrawable
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.challenge.android_template.R
import com.challenge.android_template.base.viewmodel.SystemViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.animation.*
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.*
import com.challenge.android_template.baseui.BaseTheme
import com.challenge.android_template.baseui.navigation.FeedDirections
import com.challenge.android_template.baseui.navigation.NavigationManager
import com.challenge.android_template.baseui.utilScreens.GenericErrorScreen
import com.challenge.android_template.feed.FeedUI
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var navigationManager: NavigationManager

  private val systemViewModel: SystemViewModel by viewModels()

  companion object {
    const val splashFadeDurationMillis = 300
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val splashWasDisplayed = savedInstanceState != null
    // check if we can make use of splash screen api for andriod 12
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
      if (!splashWasDisplayed) {
        displayAndroid12Animation()
      } else {
        android12AnimationAlreadyShow()
      }
    else {
      if (!splashWasDisplayed) {
        (window.decorView.background
          as TransitionDrawable).startTransition(
          splashFadeDurationMillis
        )
        // no trigger for when animations end so coroutine is used
        lifecycleScope.launch {
          delay(splashFadeDurationMillis.toLong()
            + 200)
          android12AnimationAlreadyShow()
        }
      }
      else { android12AnimationAlreadyShow() }
    }
  }

  private fun displayAndroid12Animation() {
    val splashScreen = installSplashScreen()
    splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
      splashScreenViewProvider.iconView
        .animate()
        .setDuration(splashFadeDurationMillis.toLong())
        .alpha(0f)
        .withEndAction {
          splashScreenViewProvider.remove()
          android12AnimationAlreadyShow()
        }.start()
    }
  }

  private fun android12AnimationAlreadyShow(){
    setTheme(R.style.Theme_AnimatedSplashScreen)
    setContent {
      BaseTheme {
        val navController = rememberNavController()
        navigationManager.commands.collectAsState().value.also { command ->
          if (command.destination.isNotEmpty()) {
            navController.navigate(command.destination)
          }
        }
        NavHost(
          navController = navController,
          startDestination  = FeedDirections.root.destination
        ) {
          navigation(
            startDestination = FeedDirections.feed.destination,
            route = FeedDirections.root.destination
          ) {
            composable(FeedDirections.feed.destination) {
              FeedUI(
                /*
                navController.hiltNavGraphViewModel(
                  route = FeedDirections.feed.destination
                )
                 */
                navController.hiltNavGraphViewModel(
                  route = FeedDirections.feed.destination
                )
              )
            }
          }
        }
        //GenericErrorScreen("something happen 😢")
      }
    }
  }
}