package com.venicio.venicioflix.data.model

import com.squareup.moshi.Json
import com.venicio.venicioflix.data.model.Serie

data class SeriesResponse(
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val results: MutableList<Serie>,
)