package com.challenge.android_template.baseui.navigation

import androidx.navigation.compose.NamedNavArgument

interface NavigationCommand {

  val arguments: List<NamedNavArgument>

  val destination: String
}