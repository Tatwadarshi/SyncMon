package com.ntrcaebpt.syncmon.datatypes

import com.google.gson.annotations.SerializedName

data class ThingSpeakResponse(
    val feeds: List<Feed>,
    val channel: ChannelInfo
)
data class ThingSpeakResponse2(
    val feeds: String,
    val channel: String
)

data class ChannelInfo(
    val id: Int,
    val name: String,
    val description: String?,
    val latitude: String?,
    val longitude: String?,
    val field1: String?,
    val field2: String?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("last_entry_id") val lastEntryId: Int
)

data class Feed(
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("entry_id") val entryId: Int,
    val field1: String?,
    val field2: String?
)
