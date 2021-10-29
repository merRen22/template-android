package com.challenge.android_template.database

import com.challenge.android_template.database.dao.FooDao
import com.challenge.android_template.database.entity.FooEntity.Companion.createFooEntity
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class FooDbClientTest {

  private lateinit var client: FooDbClient

  @MockK
  private lateinit var mockFooDao: FooDao

  @Before
  fun setup() {
    MockKAnnotations.init(this)

    client = FooDbClient(mockFooDao)
  }

  @Test
  fun `get all foos`() {
    runBlocking {

      coEvery { mockFooDao.getAll() } returns listOf(
        createFooEntity(1, "Shinji"),
        createFooEntity(2, "Azuka"),
        createFooEntity(3, "Rei")
      )

      val foos = client.getLocalFoos()

      assertThat(foos).hasSize(3)
      assertThat(foos[0].id).isEqualTo(1)
      assertThat(foos[0].name).isEqualTo("Shinji")
      assertThat(foos[1].id).isEqualTo(2)
      assertThat(foos[1].name).isEqualTo("Azuka")
      assertThat(foos[2].id).isEqualTo(3)
      assertThat(foos[2].name).isEqualTo("Rei")
    }
  }

}