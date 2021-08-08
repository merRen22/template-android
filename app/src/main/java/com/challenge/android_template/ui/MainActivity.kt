package com.challenge.android_template.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.challenge.android_template.base.viewmodel.SystemViewModel
import com.challenge.android_template.baseui.BaseTheme
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private val systemViewModel: SystemViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      BaseTheme(
        systemViewModel.isNightMode(),
      ) {
        ProvideWindowInsets {
          Greeting("Android")
        }
      }
    }
  }
}


@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}