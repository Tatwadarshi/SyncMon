package com.ntrcaebpt.syncmon.api_services

import com.ntrcaebpt.syncmon.datatypes.ThingSpeakResponse
import com.ntrcaebpt.syncmon.datatypes.ThingSpeakResponse2
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ThingSpeakService {
    @GET("channels/{channel_id}/feeds.json")
    suspend fun getFeeds(
        @Path("channel_id") channelId: String,
        @Query("api_key") apiKey: String? = null,
        @Query("results") results: Int? = null
    ): ThingSpeakResponse
}