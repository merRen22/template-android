package com.challenge.android_template.database.dao

import androidx.room.*
import com.challenge.android_template.database.entity.FooEntity

/**
 * Repository Dao includes generic operations for any class
 */
@Dao
abstract class FooDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insert(repositoryEntity: FooEntity)

  @Query("SELECT * FROM foo")
  abstract suspend fun getAll(): List<FooEntity>

  // TODO add more operations if needed
  /*

  @Delete
  abstract suspend fun delete(repositoryEntity: RepositoryEntity)

  @Query("DELETE FROM foo")
  abstract suspend fun deleteAll()
   */
}