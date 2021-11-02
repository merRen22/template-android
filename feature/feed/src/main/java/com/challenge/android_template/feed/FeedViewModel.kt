package com.challenge.android_template.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.android_template.repository.FooRepository
import com.challenge.android_template.base.ErrorHandler
import com.challenge.android_template.model.Foo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] to store and manage Feed data.
 */
@HiltViewModel
class FeedViewModel @Inject constructor(
  private val fooRepository: FooRepository,
  private val errorHandler: ErrorHandler
) : ViewModel() {

  private val mutableFooItems: MutableStateFlow<List<Foo>> = MutableStateFlow(emptyList())
  val fooItems: StateFlow<List<Foo>> = mutableFooItems

  /**
   * In case of makifng multile request at the same time and handleing all ther threads an errors
   * see this article https://medium.com/@elizarov/structured-concurrency-722d765aa952 and this
   * example of a request
   * https://github.com/TakuSemba/JetHub/blob/master/feature/feed/src/main/java/com/takusemba/jethub/feed/FeedViewModel.kt
   */
  init {
    viewModelScope.launch {
      runCatching {
        // make some request to the API
        fooRepository.getAllFoos()
      }.onSuccess { foos ->
        // do stuff with a live data
        mutableFooItems.value = foos
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }
}