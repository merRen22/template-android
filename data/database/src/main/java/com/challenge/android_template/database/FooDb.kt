package com.challenge.android_template.database

import com.challenge.android_template.model.Foo

/**
 * Foo DB
 */
interface FooDb {

  /**
   * insert [Foo]
   */
  suspend fun insert(foo: Foo)

  /**
   * Generic function to get a json from local Db
   */
  suspend fun getLocalFoos(): List<Foo>

  // TODO add operations as needed
  /*
    /**
   * delete [Foo]
   */
  suspend fun delete(foo: Foo)

  /**
   * delete all [Foo]
   */
  suspend fun deleteAll()
   */
}