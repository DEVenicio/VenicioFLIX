package com.venicio.venicioflix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venicio.venicioflix.data.model.EpisodeGroupResponse
import com.venicio.venicioflix.data.model.SeasonResponse
import com.venicio.venicioflix.data.repository.Repository
import com.venicio.venicioflix.view.ui.SeriesDetailsFragmentArgs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailsViewModel(
    private val rp: Repository,
    private val args: SeriesDetailsFragmentArgs
) : ViewModel() {

    private var detailsResult: SeasonResponse? = null

    private val _detailSerie = MutableLiveData<SeasonResponse?>()
    val detailSerie: LiveData<SeasonResponse?>
        get() = _detailSerie

    private val _seasonEpisodeGroup = MutableLiveData<EpisodeGroupResponse?>()
    val seasonEpisodeGroup: LiveData<EpisodeGroupResponse?>
        get() = _seasonEpisodeGroup


    init {
        getDetailsSerie()
        getEpisodeGroups()
    }


    private fun getDetailsSerie() {
        viewModelScope.launch(Dispatchers.IO) {
            detailsResult = rp.getDetails(args.idSerie)
            _detailSerie.postValue(detailsResult)
        }
    }

    private fun getEpisodeGroups() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = rp.getEpisodeGroups(args.idSerie)
            val idEpisodeGroups = response?.results?.map { idGroup ->
                idGroup.id}

            try {
                val episodes = rp.getEpisodeGroupSpecific(idEpisodeGroups?.get(0).toString())
                _seasonEpisodeGroup.postValue(episodes)
            } catch (e: Exception){

            }





        }
    }

}