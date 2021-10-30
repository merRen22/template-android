package com.challenge.android_template.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.challenge.android_template.database.dao.FooDao
import com.challenge.android_template.database.entity.FooEntity
import com.challenge.android_template.model.Foo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FooDbClientTest {
  private lateinit var fooDao: FooDao
  private lateinit var db: AppDatabase

  @Before
  fun createDb() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    db = Room.inMemoryDatabaseBuilder(
      context, AppDatabase::class.java
    ).build()
    fooDao = db.fooDao
  }

  @After
  @Throws(IOException::class)
  fun closeDb() {
    db.close()
  }

  @Test
  @Throws(Exception::class)
  fun `insert and get all foos`() {
    runBlocking {
      val foo: Foo = Foo(1, "Shinji")
      fooDao.insert(
        FooEntity.fromModel(foo)
      )
      val byName = fooDao.getAll()
      assertThat(byName).contains(foo)
      /*
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
       */
    }
  }
}