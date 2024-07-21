package com.shabeen07.hilt_di.domain

import com.shabeen07.hilt_di.models.UserResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUser() : List<UserResponseItem>

}