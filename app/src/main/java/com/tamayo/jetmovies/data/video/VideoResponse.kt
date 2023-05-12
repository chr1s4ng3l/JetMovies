package com.tamayo.jetmovies.data.video


import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("results")
    val results: List<VideoResult>? = null
)