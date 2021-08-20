package com.challenge.android_template.ui

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import androidx.navigation.navDeepLink
import com.challenge.android_template.about.About
import com.challenge.android_template.baseui.DestinationArgs
import com.challenge.android_template.baseui.DestinationDeepLink
import com.challenge.android_template.baseui.Destinations
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


/**
 * Navigation Graph to control the App navigation.
 *
 * @param startDestination the start destination of the graph
 */
@ExperimentalAnimationApi
@Composable
fun NavGraph(startDestination: String = Destinations.Home) {

  val navController = rememberAnimatedNavController()
  val context = LocalContext.current

  val actions = remember(navController) { Actions(navController, context) }
  AnimatedNavHost(navController = navController, startDestination = startDestination) {

    /*
    composable(Destinations.Home) {
      Home(
        onTaskClick = actions.openFooDetail,
        onAboutClick = actions.openAbout,
      )
    }

    composable(
      route = "${Destinations.FooDetail}/{${DestinationArgs.FooId}}",
      arguments = listOf(navArgument(DestinationArgs.FooId) { type = NavType.LongType }),
      deepLinks = listOf(navDeepLink { uriPattern = DestinationDeepLink.FooDetailPattern })
    ) { backStackEntry ->
      val arguments = requireNotNull(backStackEntry.arguments)
      TaskDetailSection(
        taskId = arguments.getLong(DestinationArgs.FooId),
        onUpPress = actions.onUpPress
      )
    }
     */

    composable(Destinations.About) {
      About(onUpPress = actions.onUpPress)
    }

  }
}


internal data class Actions(val navController: NavHostController, val context: Context) {

  val openFooDetail: (Long) -> Unit = { taskId ->
    navController.navigate("${Destinations.FooDetail}/$taskId")
  }

  val openAbout: () -> Unit = {
    navController.navigate(Destinations.About)
  }

  val onUpPress: () -> Unit = {
    navController.navigateUp()
  }
}

private const val FOO_DEEP_LINK = "app://someUrl"