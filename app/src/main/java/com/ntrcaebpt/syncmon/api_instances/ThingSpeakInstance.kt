package com.ntrcaebpt.syncmon.api_instances

import com.ntrcaebpt.syncmon.api_services.ThingSpeakService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ThingSpeakInstance {
    private const val BASE_URL = "https://api.thingspeak.com"

    val api: ThingSpeakService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ThingSpeakService::class.java)
    }
}