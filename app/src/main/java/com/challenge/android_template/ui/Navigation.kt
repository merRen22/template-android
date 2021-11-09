package com.challenge.android_template.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.challenge.android_template.baseui.navigation.FeedDirections
import com.challenge.android_template.feed.FeedUI
import com.challenge.android_template.feed.FeedViewModel

@Composable
fun Navigation(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = FeedDirections.root.destination
  ) {
    navigation(
      startDestination = FeedDirections.feed.destination,
      route = FeedDirections.root.destination
    ) {
      composable(FeedDirections.feed.destination) {
        /*
        navController.hiltNavGraphViewModel(
          route = FeedDirections.feed.destination
        )
         */
        val feedEntry = remember { navController.getBackStackEntry("connect") }
        val viewModel = hiltViewModel<FeedViewModel>(feedEntry)
        FeedUI(viewModel)
      }
    }
  }
  //GenericErrorScreen("something happen 😢")
}