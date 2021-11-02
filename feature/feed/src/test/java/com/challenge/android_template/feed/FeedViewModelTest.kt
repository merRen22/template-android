package com.challenge.android_template.feed

import com.google.common.truth.Truth.assertThat
import com.challenge.android_template.base.ErrorHandler
import com.challenge.android_template.model.Foo.Companion.createFoo
import com.challenge.android_template.repository.FooRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class FeedViewModelTest {

  @MockK
  private lateinit var fooRepository: FooRepository

  @MockK
  private lateinit var errorHandler: ErrorHandler

  private val dispatcher = TestCoroutineDispatcher()

  @Before
  fun setUp() {
    MockKAnnotations.init(this)
    Dispatchers.setMain(dispatcher)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `initial state`() = runBlockingTest {
    val foos = listOf(
      createFoo(id = 1),
      createFoo(id = 2),
      createFoo(id = 3)
    )

    coEvery { fooRepository.getAllFoos() } returns foos

    val viewModel = FeedViewModel(fooRepository, errorHandler)

    assertThat(viewModel.fooItems.value).isEqualTo(foos)
  }
}