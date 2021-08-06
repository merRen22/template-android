package com.challenge.android_template.api.response

import com.challenge.android_template.model.Foo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response data for [Foo]
 */
@Serializable
class FooReponse {
  @SerialName("id")
  var id: Int? = null

  @SerialName("name")
  var name: String? = null

  fun toModel() = Foo(
    id = id ?: throw IllegalArgumentException("no id specified"),
    name = name ?: throw IllegalArgumentException("no name specified"),
  )
}