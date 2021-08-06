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

  // TODO add operations as needed
  /*
    /**
   * get all [Foo]
   */
  suspend fun getAll(): List<Foo>

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