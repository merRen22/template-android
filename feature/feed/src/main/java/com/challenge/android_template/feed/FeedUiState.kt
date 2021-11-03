package com.challenge.android_template.feed

import com.challenge.android_template.model.Foo

data class FeedUiState(
  val foos: List<Foo> = emptyList(),
  val isLoading: Boolean = false
) {

  companion object {

    val EMPTY = FeedUiState()
  }
}