package com.challenge.android_template.baseui

import android.net.Uri
import androidx.core.net.toUri

// TODO update as soon as compose navigation allows the use on dynamic modules
/**
 * Represents the possible destinations of the app via Compose Navigation. The destinations represents
 * a flow where a backstack is required, so the tabs inside the Home destination is a simple
 * navigation.
 */
object Destinations {

  /**
   * Home destination.
   */
  const val Home = "home"

  /**
   * Task detail destination.
   */
  const val FooDetail = "task_detail"

  /**
   * About destination.
   */
  const val About = "about"
}

// TODO update with the args for your own app
/**
 * Represents the arguments to be passed through the [Destinations].
 */
object DestinationArgs {

  /**
   * Argument to be passed to [Destinations.FooDetail] representing the foo id to be detailed.
   */
  const val FooId = "foo_id"
}


/**
 * Represents the Deep Links to implicit navigate through the application, like PendingIntent.
 */
object DestinationDeepLink {

  private val BaseUri = "app://someUrl".toUri()

  /**
   * Deep link pattern to be registered in [Destinations.FooDetail] composable.
   */
  val FooDetailPattern = "$BaseUri/${DestinationArgs.FooId}={${DestinationArgs.FooId}}"


  /**
   * Returns the [Destinations.FooDetail] deep link with the argument set.
   *
   * @return the [Destinations.FooDetail] deep link with the argument set
   */
  fun getFooDetailUri(taskId: Long): Uri =
    "$BaseUri/${DestinationArgs.FooId}=$taskId".toUri()
}