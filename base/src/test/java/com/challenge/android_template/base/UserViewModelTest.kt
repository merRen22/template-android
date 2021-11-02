package com.challenge.android_template.base

import com.google.common.truth.Truth.assertThat
import com.challenge.android_template.base.viewmodel.UserViewModel
import com.challenge.android_template.model.Foo.Companion.createFoo
import com.challenge.android_template.repository.FooRepository
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.just
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

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
    coEvery { fooRepository.getLocalFoos() } returns foos

    val viewModel = UserViewModel(fooRepository, errorHandler)

    assertThat(viewModel.fooItems.value).isEqualTo(foos)
  }

  @Test
  fun `save foo`() = runBlockingTest {
    val foos = listOf(
      createFoo(id = 1),
      createFoo(id = 2),
      createFoo(id = 3)
    )
    coEvery { fooRepository.getLocalFoos() } returns foos
    coEvery { fooRepository.insert(any()) } just Runs

    val viewModel = UserViewModel(fooRepository, errorHandler)

    val fooToBeAdded = createFoo(id = 4)
    viewModel.saveFoo(fooToBeAdded)

    assertThat(viewModel.fooItems.value).isEqualTo(foos + fooToBeAdded)
  }
}