package com.challenge.android_template.repository

import com.challenge.android_template.api.FooApi
import com.challenge.android_template.database.FooDb
import com.challenge.android_template.model.Foo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for [Foo]
 */
@Singleton
class FooRepository @Inject constructor(
  private val fooApi: FooApi,
  private val fooDb: FooDb
) {

  /**
   * Generic method to interact with the local DB
   */
  suspend fun insert(foo: Foo) {
    return fooDb.insert(foo)
  }

  /**
   * Generic method to interact with an API
   */
  suspend fun getLocalFoos() = fooDb.getLocalFoos()

  /**
   * Generic method to interact with an API
   */
  suspend fun getAllFoos() = fooApi.getAllFoos()
}