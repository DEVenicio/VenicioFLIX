package com.venicio.venicioflix.data.model

import com.squareup.moshi.Json

data class Serie(

    @Json(name = "backdrop_path")
    val backdropPath: String?,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)