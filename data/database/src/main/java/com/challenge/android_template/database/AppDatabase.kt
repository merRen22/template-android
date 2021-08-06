package com.challenge.android_template.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.android_template.database.dao.FooDao
import com.challenge.android_template.database.entity.FooEntity

/**
 * App Database
 */
@Database(entities = [FooEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

  abstract val fooDao: FooDao
}