package com.challenge.android_template.baseui.navigation

import com.challenge.android_template.baseui.navigation.FeedDirections.Default
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * For more info on how to configure this when using a modular app check this 👇
 * https://medium.com/google-developer-experts/modular-navigation-with-jetpack-compose-fda9f6b2bef7
 */

object NavigationManager {

  var commands = MutableStateFlow(Default)

  fun navigate(
    directions: NavigationCommand
  ) {
    commands.value = directions
  }

}