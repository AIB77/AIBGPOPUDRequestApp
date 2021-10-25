package com.example.aib_gpopud_requestapp

import android.widget.EditText
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @Headers("Content-Type: application/json")
    @GET("/test/")
    fun getUser(): Call<List<Users.UserDetails>>


    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun addUser(@Body userData: Users.UserDetails): Call<Users.UserDetails>

    @Headers("Content-Type: application/json")
    @PUT("/test/{id}")
    fun updateusers(@Path("id") id: EditText):Call<Users.UserDetails>

    @Headers("Content-Type: application/json")
    @DELETE("/test/{id}")
    fun deleteusers(@Path("id") id: Int):Call<Void>
}