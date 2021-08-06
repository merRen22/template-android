package com.challenge.android_template.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.android_template.base.ErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] to store and manage user-related data.
 * This should be Activity-Scope, because the data is used across screens.
 */

class UserViewModel{

}
/*
@HiltViewModel
class UserViewModel @Inject constructor(
  // add necessary repositories from data module,
  private val errorHandler: ErrorHandler
) : ViewModel() {

  // list of items request from somewhere
  private val mutableFooRepositories: MutableLiveData<List<Foo>> = MutableLiveData()
  val fooRepositories: LiveData<List<Foo>> = mutableFooRepositories

  init {
    viewModelScope.launch {
      runCatching {
        // find some internal data DB or API
      }.onSuccess { repos ->
        // do stuff with a live data
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }

}

 */