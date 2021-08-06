package com.challenge.android_template.database.di

import android.app.Application
import androidx.room.Room
import com.challenge.android_template.database.AppDatabase
import com.challenge.android_template.database.FooDb
import com.challenge.android_template.database.FooDbClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Database Module
 * Define all the database-related classes that need to be provided in the scope of Application.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

  @Provides
  @Singleton
  fun provideAppDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "database-name").build()
  }

  // TODO change to your own entities
  @Provides
  @Singleton
  fun provideFooDb(appDatabase: AppDatabase): FooDb {
    return FooDbClient(appDatabase.fooDao)
  }
}