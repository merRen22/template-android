package com.challenge.android_template.api.di

import com.challenge.android_template.api.FooApi
import com.challenge.android_template.api.FooApiClient
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @ExperimentalSerializationApi
  @Singleton
  @Provides
  fun provideConverterFactory(): Converter.Factory {
    return Json {
      isLenient = true
      ignoreUnknownKeys = true
    }.asConverterFactory("application/json".toMediaType())
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .connectTimeout(10L, TimeUnit.SECONDS)
      .writeTimeout(10L, TimeUnit.SECONDS)
      .readTimeout(30L, TimeUnit.SECONDS)
      .build()
  }

  @Singleton
  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl("https://gist.githubusercontent.com/merRen22/")
      .addConverterFactory(converterFactory)
      .build()
  }

  @Singleton
  @Provides
  fun provideFooApi(retrofit: Retrofit): FooApi {
    return FooApiClient(retrofit)
  }

}