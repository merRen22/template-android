package com.challenge.android_template.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Generic [ViewModel] use to interact with global settings for the app
 */
@HiltViewModel
class SystemViewModel @Inject constructor() : ViewModel() {

  private val mutableIsNightMode: MutableLiveData<Boolean> = MutableLiveData(false)
  val isNightMode: LiveData<Boolean> get() = mutableIsNightMode

  fun isNightMode(): Boolean {
    return requireNotNull(isNightMode.value)
  }

  fun setNightMode(isEnabled: Boolean) {
    if (mutableIsNightMode.value != isEnabled) {
      mutableIsNightMode.value = isEnabled
    }
  }
}