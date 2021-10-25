package com.venicio.venicioflix.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.venicio.venicioflix.data.model.EpisodeGroup
import com.venicio.venicioflix.data.model.Season
import com.venicio.venicioflix.data.network.TmdbService
import com.venicio.venicioflix.data.repository.Repository
import com.venicio.venicioflix.databinding.FragmentSeriesDetailsBinding
import com.venicio.venicioflix.view.adapter.RecyclerEpisodesAdapter
import com.venicio.venicioflix.view.adapter.RecyclerSeasonsAdapter
import com.venicio.venicioflix.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class SeriesDetailsFragment : Fragment() {


    private lateinit var binding: FragmentSeriesDetailsBinding
    private lateinit var adapterEP : RecyclerEpisodesAdapter



    private val args by navArgs<SeriesDetailsFragmentArgs>()
     val detailsViewModel : DetailsViewModel by viewModel{
        parametersOf(Repository(), args)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

         binding = FragmentSeriesDetailsBinding.inflate(layoutInflater, container, false)

        setupDetailObserver()
        setupRecyclerSeasons(listOf())
        setupRecyclerEP(listOf())

        return (binding.root)


    }

    private fun setupRecyclerEP(episode: List<EpisodeGroup>) {
            adapterEP = RecyclerEpisodesAdapter(listOf())

    }

    private fun setupRecyclerSeasons(season: List<Season?>) {
        val recycler: RecyclerView = binding.rvSeasons
        recycler.setHasFixedSize(true)
        recycler.adapter = RecyclerSeasonsAdapter(season as List<Season>)
    }

    private fun setupDetailObserver() {
        detailsViewModel.detailSerie.observe(viewLifecycleOwner, Observer { responseDetail ->
                binding.tvSerieDetail.text = responseDetail?.name

            Glide.with(binding.ivBackDropDetail)
                .load(TmdbService.TMDB_IMAGE_BASE_URL + responseDetail?.backdrop_path )
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivBackDropDetail)

            if (responseDetail != null) {
                setupRecyclerSeasons(responseDetail.seasons)
            }
        })

        detailsViewModel.seasonEpisodeGroup.observe(viewLifecycleOwner, Observer { responseEP ->
            if (responseEP != null) {

                responseEP.groups

                setupRecyclerEP(responseEP.groups)
            }

        })



    }


}