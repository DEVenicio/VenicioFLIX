package com.venicio.venicioflix.viewmodel

import androidx.lifecycle.*
import com.venicio.venicioflix.data.model.SeriesResponse
import com.venicio.venicioflix.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeriesViewModel(private val rp: Repository) : ViewModel() {

    private val _getTopRated = MutableLiveData<SeriesResponse>()
    val getTopRated: LiveData<SeriesResponse>
    get() = _getTopRated

    private val _getComedy = MutableLiveData<SeriesResponse>()
    val getComedy: LiveData<SeriesResponse>
    get() = _getComedy

    private val _getRomance = MutableLiveData<SeriesResponse>()
    val getRomance: LiveData<SeriesResponse>
    get() = _getRomance

    private val _getPopular = MutableLiveData<SeriesResponse>()
    val getPopular: LiveData<SeriesResponse>
    get() = _getPopular


    init {
        getTopRatedList()
        getComedyList()
        getRomanceList()
        getPopularList()
    }

    private fun getTopRatedList() {
        viewModelScope.launch(Dispatchers.IO) {
            _getTopRated.postValue(rp.getTopRated())
        }
    }

    private fun getComedyList() {
        viewModelScope.launch(Dispatchers.IO) {
            _getComedy.postValue(rp.getComedy())
        }
    }

    private fun getRomanceList() {
        viewModelScope.launch(Dispatchers.IO) {
            _getRomance.postValue(rp.getRomance())
        }
    }

    private fun getPopularList() {
        viewModelScope.launch(Dispatchers.IO) {
            _getPopular.postValue(rp.getPopular())
        }
    }

}

