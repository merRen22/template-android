package com.challenge.android_template.api

import com.google.common.truth.Truth.assertThat
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit

@RunWith(JUnit4::class)
class FooApiClientTest {

  @get:Rule
  val mockWebServer = MockWebServer()

  private lateinit var client: FooApiClient

  @Before
  fun setUp() {
    val converterFactory = Json {
      isLenient = true
      ignoreUnknownKeys = true
    }.asConverterFactory("application/json".toMediaType())
    val retrofit = Retrofit.Builder()
      .baseUrl(mockWebServer.url("/").toString())
      .addConverterFactory(converterFactory)
      .build()
    client = FooApiClient(retrofit)
  }


  @Test
  fun `get foos`() {
    runBlocking {
      val json =
        """
          {
            "items" :[
              {
                  "id": 1,
                  "name": "elon"
              },    
              {
                  "id": 2,
                  "name": "jeff"
              }
            ]
          }
          """.trimIndent()

      mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(json))

      val foos = client.getAllFoos()

      assertThat(foos.size).isAtLeast(1)
      assertThat(foos.first().name).isEqualTo("elon")
    }
  }
}