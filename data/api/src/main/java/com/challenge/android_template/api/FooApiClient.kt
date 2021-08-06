package com.challenge.android_template.api

import com.challenge.android_template.api.response.FooReponse
import com.challenge.android_template.api.response.ListResponse
import com.challenge.android_template.model.Foo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.http.GET

/**
 * Foo API client
 */
class FooApiClient(retrofit: Retrofit) : FooApi {

  // TODO specify your request here
  interface Service {

    // request to a json host in github gist
    @GET("1075d549bd60c076ad13d021f7f1b9bc/raw/1d70f48ce09ec6d8cbb81968754a5f7bbc903fca/foo_test.json")
    suspend fun getAllFoos(
    ): ListResponse<FooReponse>
  }

  private val service = retrofit.create(Service::class.java)

  override suspend fun getAllFoos(): List<Foo> {
    return withContext(IO) {
      service.getAllFoos().items?.map { response ->
        response.toModel()
      } ?: emptyList()
    }
  }
}