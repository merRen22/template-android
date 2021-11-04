package com.challenge.android_template.baseui.navigation

import androidx.navigation.NamedNavArgument


object AboutDirections {

  val authentication  = object : NavigationCommand {

    override val arguments = emptyList<NamedNavArgument>()

    override val destination = "about"

  }
}