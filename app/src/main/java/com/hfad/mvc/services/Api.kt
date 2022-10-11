package com.hfad.mvc.services

import com.hfad.mvc.model.User
import com.hfad.mvc.model.Users
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("users")
    fun getUsers(): Call<ArrayList<Users>>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Call<User>

    companion object {
        var BASE_URL = "https://api.github.com/"
        fun create(): Api {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}