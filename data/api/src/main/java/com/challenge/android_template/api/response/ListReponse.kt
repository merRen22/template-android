package com.challenge.android_template.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response data with [items] in it.
 */
@Serializable
class ListResponse<T : Any> {

  @SerialName("items")
  var items: List<T>? = null
}