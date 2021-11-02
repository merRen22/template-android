package com.challenge.android_template.model

import org.jetbrains.annotations.TestOnly

data class Foo(
  val id: Int,
  val name: String
) {
  companion object {
    @TestOnly
    fun createFoo(
      id: Int = -1,
      name: String = "",
    ) = Foo(
      id = id,
      name = name,
    )
  }
}