package com.challenge.android_template.baseui.navigation

import androidx.navigation.NamedNavArgument

object FeedDirections {

  /**
   * So here 👇 you would specify the params we are going to use and their keys
   * in our case none args are used
   *   private val KEY_USER_ID = "userId"
   *   val route = "dashboard/{$KEY_USER_ID}"
   */
  val route = "feed"

  /**
   * You would declare you arguments as below in a list
   *   val arguments = listOf(
   *    navArgument(KEY_USER_ID) { type = NavType.StringType }
   *   )
   */

  // you could also add params to this roo to set args
  val root = object : NavigationCommand {

    // here you would put the argument list
    override val arguments = emptyList<NamedNavArgument>()

    override val destination = "connect"

  }

  val feed = object : NavigationCommand {

    // here you would put the argument list
    override val arguments = emptyList<NamedNavArgument>()

    override val destination = "feed"

  }

  val Default = object : NavigationCommand {

    override val arguments = emptyList<NamedNavArgument>()

    override val destination = ""

  }

}