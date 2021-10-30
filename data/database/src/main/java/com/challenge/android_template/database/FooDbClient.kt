package com.challenge.android_template.database

import com.challenge.android_template.database.dao.FooDao
import com.challenge.android_template.database.entity.FooEntity
import com.challenge.android_template.model.Foo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * Repository DB client
 */
class FooDbClient(private val fooDao: FooDao) : FooDb {

  override suspend fun insert(foo: Foo) {
    return withContext(IO) {
      fooDao.insert(FooEntity.fromModel(foo))
    }
  }

  override suspend fun getLocalFoos(): List<Foo> {
    return withContext(IO) {
      fooDao.getAll().map { entity -> entity.toModel() }
    }
  }
}