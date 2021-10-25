package com.venicio.venicioflix.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.venicio.venicioflix.data.model.Serie
import com.venicio.venicioflix.data.network.TmdbService
import com.venicio.venicioflix.databinding.ItemCoverSerieBinding
import com.venicio.venicioflix.view.ui.SeriesFragmentDirections


class RecyclerGenresAdapter(
    var seriesItems: List<Serie>
) : RecyclerView.Adapter<RecyclerGenresAdapter.SeriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCoverSerieBinding.inflate(inflater, parent, false)

        return SeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bindSeries(seriesItems[position])
    }


    override fun getItemCount(): Int = seriesItems.size


    class SeriesViewHolder(val binding: ItemCoverSerieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindSeries(serie: Serie) {
            binding.tvNameSerie.text = serie.name

            Glide.with(binding.ivCover)
                .load(TmdbService.TMDB_IMAGE_BASE_URL + serie.poster_path)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivCover)

            binding.cvSerie.setOnClickListener {

                    val idDetail = serie.id

                        val direction =
                        SeriesFragmentDirections.actionSeriesFragmentToSeriesDetailsFragment(idDetail)
                        binding.root.findNavController().navigate(direction)
            }
        }
    }


}