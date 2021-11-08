package com.challenge.android_template.baseui.utilScreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.challenge.android_template.baseui.R


@ExperimentalAnimationApi
@Composable
fun GenericErrorScreen(error: String) {
  var visible by remember { mutableStateOf(false) }

  val splashFadeDurationMillis = 300

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
              durationMillis = splashFadeDurationMillis,
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
              text = error,
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