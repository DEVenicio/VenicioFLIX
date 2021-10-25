package com.venicio.venicioflix.data.network


import com.venicio.venicioflix.data.model.EpisodeGroupsResponse
import com.venicio.venicioflix.data.model.EpisodeGroupResponse
import com.venicio.venicioflix.data.model.SeasonResponse
import com.venicio.venicioflix.data.model.SeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {

    @GET("$TMDB_API_VERSION/tv/top_rated")
    suspend fun fetcherTopRatedSeriesAsync(
        @Query("api_key") apiKey: String,
        @Query("language") language: String

    ): SeriesResponse?

    @GET("$TMDB_API_VERSION/tv/popular")
    suspend fun fetchComedySeriesAsync(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("with_genres") genresId: String
    ): SeriesResponse?

    @GET("$TMDB_API_VERSION/tv/popular")
    suspend fun fetchRomanceSeriesAsync(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("with_genres") genresId: String
    ): SeriesResponse?

    @GET("$TMDB_API_VERSION/tv/popular")
    suspend fun fetchPopularSeriesAsync(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): SeriesResponse?

    @GET("$TMDB_API_VERSION/tv/{tv_id}")
    suspend fun fetcherDetailsSeriesAsync(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): SeasonResponse?


    @GET("$TMDB_API_VERSION/tv/{tv_id}/episode_groups")
    suspend fun fetcherEpisodeGroupsAsync(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): EpisodeGroupsResponse?


    @GET("$TMDB_API_VERSION/tv/episode_group/{id}")
    suspend fun fetcherEpisodeGroupSpecificAsync(
        @Path("id") id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): EpisodeGroupResponse?




    companion object {
        const val TMDB_BASE_URL = "https://api.themoviedb.org/"
        const val TMDB_API_QUERY = "api_key"
        const val TMDB_API_KEY = "27490b1bf49c0e5ffaa07dfd947e9605"
        const val TMDB_API_VERSION = "3"
        const val TMDB_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    }
}