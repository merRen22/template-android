package com.challenge.android_template.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.challenge.android_template.database.dao.FooDao
import com.challenge.android_template.database.entity.FooEntity
import com.challenge.android_template.model.Foo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * This is an Instrumented test which is the type recommend when unit testing db
 * mainly because this way we use the sqlite version from an emulator or real
 * device instead of the version on our local machine.
 */

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class FooInstrumentedDbClientTest {

  private lateinit var fooDao: FooDao
  private lateinit var db: AppDatabase

  @Before
  fun setup() {
    db = Room.inMemoryDatabaseBuilder(
      ApplicationProvider.getApplicationContext(),
      AppDatabase::class.java
    ).allowMainThreadQueries().build()
    fooDao = db.fooDao
  }

  @After
  @Throws(IOException::class)
  fun teardown() {
    db.close()
  }

  @Test
  @Throws(Exception::class)
  fun `insert and get all foos`() = runBlockingTest {
    val foo = Foo(1, "Shinji")
    fooDao.insert(FooEntity.fromModel(foo))
    val byName = fooDao.getAll()
    assertThat(byName).contains(foo)
  }

}