package com.challenge.android_template.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.challenge.android_template.model.Foo
import org.jetbrains.annotations.TestOnly


@Entity(tableName = "foo")
class FooEntity(
  @PrimaryKey val id: Int,
  val name: String
) {

  fun toModel() = Foo(
    id = id,
    name = name
  )

  companion object {

    fun fromModel(foo: Foo): FooEntity {
      return FooEntity(
        id = foo.id,
        name = foo.name
      )
    }

    @TestOnly
    fun createFooEntity(
      id: Int = -1,
      name: String = "dungus"
    ) = FooEntity(
      id = id,
      name = name
    )

  }
}