package com.challenge.android_template.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.android_template.base.ErrorHandler
import com.challenge.android_template.model.Foo
import com.challenge.android_template.repository.FooRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] to store and manage user-related data.
 * This should be Activity-Scope, because the data is used across screens.
 * For this sample we request all Foos collected by the user
 */
@HiltViewModel
class UserViewModel @Inject constructor(
  // add necessary repositories from data module,
  private val fooRepository: FooRepository,
  private val errorHandler: ErrorHandler
) : ViewModel() {

  // list of items request from somewhere
  private val mutableFooRepositories: MutableLiveData<List<Foo>> = MutableLiveData()
  val fooRepositories: LiveData<List<Foo>> = mutableFooRepositories

  init {
    viewModelScope.launch {
      runCatching {
        // find some internal data DB or API
        fooRepository.getLocalFoos()
      }.onSuccess { foos ->
        // do stuff with a live data
        mutableFooRepositories.value = foos
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }

}