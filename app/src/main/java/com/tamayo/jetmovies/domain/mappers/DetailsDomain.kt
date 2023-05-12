package com.tamayo.jetmovies.domain.mappers

import com.tamayo.jetmovies.data.details.DetailsResponse
import com.tamayo.jetmovies.data.details.Genre
import com.tamayo.jetmovies.data.details.ProductionCompany
import com.tamayo.jetmovies.data.details.ProductionCountry
import com.tamayo.jetmovies.data.details.SpokenLanguage

data class DetailsDomain(
    val adult: Boolean?,
    val backdropPath: String?,
    val belongsToCollection: Any?,
    val budget: Int?,
    val genres: List<Genre>?,
    val homepage: String?,
    val id: Int?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompany>?,
    val productionCountries: List<ProductionCountry>?,
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguage>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)

fun DetailsResponse?.mapToDomain(): DetailsDomain =
        DetailsDomain(
            adult = this?.adult,
            backdropPath = this?.backdropPath,
            belongsToCollection = this?.belongsToCollection,
            budget = this?.budget,
            genres = this?.genres?.map { genre ->
                Genre(
                    id = genre?.id,
                    name = genre?.name
                )
            },
            homepage = this?.homepage,
            id = this?.id,
            imdbId = this?.imdbId,
            originalLanguage = this?.originalLanguage,
            originalTitle = this?.originalTitle,
            overview = this?.overview,
            popularity = this?.popularity,
            posterPath = this?.posterPath,
            productionCompanies = this?.productionCompanies?.map { company ->
                ProductionCompany(
                    id = company?.id,
                    logoPath = company?.logoPath,
                    name = company?.name,
                    originCountry = company?.originCountry
                )
            },
            productionCountries = this?.productionCountries?.map { country ->
                ProductionCountry(
                    iso31661 = country?.iso31661,
                    name = country?.name
                )
            },
            releaseDate = this?.releaseDate,
            revenue = this?.revenue,
            runtime = this?.runtime,
            spokenLanguages = this?.spokenLanguages?.map { language ->
                SpokenLanguage(
                    iso6391 = language?.iso6391,
                    name = language?.name
                )
            },
            status = this?.status,
            tagline = this?.tagline,
            title = this?.title,
            video = this?.video,
            voteAverage = this?.voteAverage,
            voteCount = this?.voteCount
        )
