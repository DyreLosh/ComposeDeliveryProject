package com.example.composedeliveryproject.https

import com.example.composedeliveryproject.https.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @POST("auth/register")
    fun userCreate(@Body userCreate: UserCreate) : Call<Unit>

    @POST("auth/login")
    fun userLogin(@Body userLogin: UserLogin) : Call<Token>

    @GET("dishes/version")
    fun getVersion() : Call<Version>

    @GET("dishes")
    fun getDishes(@Query("version") version: String) : Call<List<Dishes>>
}