package com.challenge.android_template.base.di

import android.app.Application
import com.challenge.android_template.base.AppErrorHandler
import com.challenge.android_template.base.ErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

  @Singleton
  @Provides
  fun provideErrorHandler(context: Application): ErrorHandler {
    return AppErrorHandler(context)
  }

}