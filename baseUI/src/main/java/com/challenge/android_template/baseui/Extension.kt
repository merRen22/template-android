package com.challenge.android_template.baseui

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

/**
 * Opens the given url in string format.
 *
 * @param url the url in string format
 */
fun Context.openUrl(url: String) {
  with(Intent(Intent.ACTION_VIEW)) {
    this.data = url.toUri()
    startActivity(this)
  }
}