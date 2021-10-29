package com.challenge.android_template.ui

import android.graphics.drawable.TransitionDrawable
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.challenge.android_template.R
import com.challenge.android_template.base.viewmodel.SystemViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {

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
          setContent {
            StartScreen()
          }
        }.start()
    }
  }

  private fun android12AnimationAlreadyShow(){
    setTheme(R.style.Theme_AnimatedSplashScreen)
    setContent {
      StartScreen()
    }
  }
}

@ExperimentalAnimationApi
@Composable
fun StartScreen() {
  var visible by remember { mutableStateOf(false) }

  Scaffold(
    content = {
      Box(
        modifier = Modifier
          .background(
            // todo fix
            colorResource(id = android.R.color.holo_blue_light)
          )
          .fillMaxSize(),
      ) {
        AnimatedVisibility(
          visible = visible,
          enter = slideInVertically(
            // Slide in from top
            initialOffsetY = { -it },
            animationSpec = tween(
              durationMillis = MainActivity.splashFadeDurationMillis,
              easing = LinearEasing
            )
          ),
        ) {
          Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
              .padding(0.dp, 0.dp, 0.dp, 0.dp)
              .background(
                // todo fix
                colorResource(id = android.R.color.holo_blue_light)
              )
              .fillMaxSize()
          ) {
            Text(
              stringResource(
                id = R.string.app_name
              ),
              fontSize = 36.sp,
              modifier = Modifier.padding(
                bottom =
                dimensionResource(R.dimen.start_content_title_margin_bottom)
              ),
              color = Color.White,
              fontWeight = FontWeight.Bold
            )
            Box(
              modifier = Modifier
                .height(
                  dimensionResource(R.dimen.start_content_size)
                )
                .width(
                  dimensionResource(R.dimen.start_content_size)
                )
                .clip(
                  RoundedCornerShape(8.dp)
                )
                .background(color = Color.White)
            )
          }
        }
      }
      LaunchedEffect(true) {
        visible = true
      }
    }
  )
}