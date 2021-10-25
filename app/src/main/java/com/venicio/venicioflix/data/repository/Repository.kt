package com.venicio.venicioflix.data.repository


import com.venicio.venicioflix.data.network.TmdbClient
import com.venicio.venicioflix.data.network.TmdbService

class Repository {

    private val service = TmdbClient.getInstance()


    suspend fun getPopular() = service.fetchPopularSeriesAsync(TmdbService.TMDB_API_KEY,"pt-BR")

    suspend fun getComedy() = service.fetchComedySeriesAsync(TmdbService.TMDB_API_KEY,"pt-BR","35")

    suspend fun getRomance() = service.fetchRomanceSeriesAsync(TmdbService.TMDB_API_KEY,"pt-BR","10749")

    suspend fun getTopRated() = service.fetcherTopRatedSeriesAsync(TmdbService.TMDB_API_KEY,"pt-BR")

    suspend fun getDetails(id: Int) = service.fetcherDetailsSeriesAsync( id,
        TmdbService.TMDB_API_KEY,"pt-BR")

    suspend fun getEpisodeGroups(id:Int) = service.fetcherEpisodeGroupsAsync( id, TmdbService.TMDB_API_KEY,"pt-BR")

    suspend fun getEpisodeGroupSpecific(id: String) = service.fetcherEpisodeGroupSpecificAsync( id, TmdbService.TMDB_API_KEY,"pt-BR")

}