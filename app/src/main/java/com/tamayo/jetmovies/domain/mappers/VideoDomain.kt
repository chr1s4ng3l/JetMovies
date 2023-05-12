package com.tamayo.jetmovies.domain.mappers

import com.tamayo.jetmovies.data.video.VideoResult

data class VideoDomain(
    val id: String?,
    val iso31661: String?,
    val iso6391: String?,
    val key: String?,
    val name: String?,
    val official: Boolean?,
    val publishedAt: String?,
    val site: String?,
    val size: Int?,
    val type: String?
)

fun List<VideoResult>?.mapToDomain(): List<VideoDomain> =
    this?.map {
        VideoDomain(
            id = it.id,
            iso31661 = it.iso31661,
            iso6391 = it.iso6391,
            key = it.key,
            name = it.name,
            official = it.official,
            publishedAt = it.publishedAt,
            site = it.site,
            size = it.size,
            type = it.type
        )
    }?: emptyList()
