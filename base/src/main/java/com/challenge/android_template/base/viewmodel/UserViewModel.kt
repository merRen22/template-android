package com.challenge.android_template.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.android_template.base.ErrorHandler
import com.challenge.android_template.model.Foo
import com.challenge.android_template.repository.FooRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
  private val mutableFooItems: MutableStateFlow<List<Foo>> = MutableStateFlow(emptyList())
  val fooItems: StateFlow<List<Foo>> = mutableFooItems

  init {
    viewModelScope.launch {
      runCatching {
        // find some internal data DB or API
        fooRepository.getLocalFoos()
      }.onSuccess { foos ->
        // do stuff with a live data
        mutableFooItems.value = foos
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }

  fun saveFoo(foo: Foo) {
    viewModelScope.launch {
      runCatching {
        fooRepository.insert(foo)
        val foos = mutableFooItems.value.toMutableList()
        foos.apply { add(foo) }
      }.onSuccess { foos ->
        // do stuff with a live data
        mutableFooItems.value = foos
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }

}