package com.challenge.android_template.base

import android.util.Log

interface ErrorHandler {

  fun handleError(error: Throwable)

  companion object {

    private const val TAG = "ErrorHandler"

    val DEFAULT = object : ErrorHandler {
      override fun handleError(error: Throwable) {
        Log.e(TAG, "[${error.message}]")
      }
    }
  }
}