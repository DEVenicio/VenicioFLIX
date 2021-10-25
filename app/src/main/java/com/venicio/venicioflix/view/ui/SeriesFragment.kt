package com.venicio.venicioflix.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.venicio.venicioflix.data.model.Serie
import com.venicio.venicioflix.data.network.TmdbService
import com.venicio.venicioflix.data.repository.Repository
import com.venicio.venicioflix.databinding.FragmentSeriesBinding
import com.venicio.venicioflix.view.adapter.RecyclerGenresAdapter
import com.venicio.venicioflix.viewmodel.SeriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*

class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding
    private lateinit var recycler: RecyclerView
    private val seriesViewModel : SeriesViewModel by viewModel{
        parametersOf(Repository())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeriesBinding.inflate(layoutInflater)

        setupFirstRecycler(listOf())
        setupSecondRecycler(listOf())
        setupThirdRecycler(listOf())
        setupObservers()
        return (binding.root)
    }

    private fun setupFirstRecycler(all: List<Serie>) {
        recycler = binding.rvFirstGenre
        recycler.adapter = RecyclerGenresAdapter(all)
    }

    private fun setupSecondRecycler(all: List<Serie>) {
        recycler = binding.rvSecondGenre
        recycler.adapter = RecyclerGenresAdapter(all)
    }

    private fun setupThirdRecycler(all: List<Serie>) {
        recycler = binding.rvThirdGenre
        recycler.adapter = RecyclerGenresAdapter(all)
    }

    private fun setupObservers() {


        seriesViewModel.getTopRated.observe(viewLifecycleOwner, Observer { responseTop ->
            if (responseTop != null) {

                val list = responseTop.results.map { index ->
                    index.popularity
                }

                val mostPopular = Collections.max(list)
                val idxPosition = list.indexOf(mostPopular)

                binding.tvSerieTop.text = responseTop.results[idxPosition].name

                Glide.with(binding.ivSerieTop)
                    .load(TmdbService.TMDB_IMAGE_BASE_URL + responseTop.results[idxPosition].backdropPath)
                    .into(binding.ivSerieTop)
            }
        })

        seriesViewModel.getComedy.observe(viewLifecycleOwner, Observer { responseComedy ->
            if (responseComedy != null) {
                setupFirstRecycler(responseComedy.results)
            }
        })

        seriesViewModel.getRomance.observe(viewLifecycleOwner, Observer { responseRomance ->
            if (responseRomance != null) {
                setupSecondRecycler(responseRomance.results)
            }
        })

        seriesViewModel.getPopular.observe(viewLifecycleOwner, Observer { responsePopular ->
            if (responsePopular != null) {
                setupThirdRecycler(responsePopular.results)
            }

            binding.progressBarSerie.visibility = View.GONE
            binding.ScrollSerie.visibility  = View.VISIBLE
        })


    }
}



