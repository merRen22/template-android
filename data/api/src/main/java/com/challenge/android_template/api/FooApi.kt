package com.challenge.android_template.api

import com.challenge.android_template.model.Foo

/**
 * Foo API
 */
interface FooApi {

  /**
   * Generic function to get a json
   */
  suspend fun getAllFoos(): List<Foo>

  // TODO add your own request and entities
}