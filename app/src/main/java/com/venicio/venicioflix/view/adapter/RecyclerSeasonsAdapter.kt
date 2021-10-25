package com.venicio.venicioflix.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.venicio.venicioflix.data.model.Episode
import com.venicio.venicioflix.data.model.Season
import com.venicio.venicioflix.data.network.TmdbService
import com.venicio.venicioflix.databinding.ItemSeasonDetailBinding

class RecyclerSeasonsAdapter(
    private val seasonsList: List<Season>
) :  RecyclerView.Adapter<RecyclerSeasonsAdapter.SeasonsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSeasonDetailBinding.inflate(inflater, parent, false)

        return SeasonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeasonsViewHolder, position: Int) {
        holder.bindSeasons(seasonsList[position])
    }

    override fun getItemCount(): Int = seasonsList.size

    class SeasonsViewHolder(val binding: ItemSeasonDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val rr : RelativeLayout = binding.expandableLayout

        fun bindSeasons(season: Season?) {

            binding.tvSeasonDetail.text = season?.name
            binding.tvOverviewSeasonDetail.text = season?.overview

            Glide.with(binding.ivPosterDetail)
                .load(TmdbService.TMDB_IMAGE_BASE_URL + season?.poster_path)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivPosterDetail)
        }

        fun bindEps(episodes: Episode) {

            binding.ivArrowDown.setOnClickListener {
                binding.expandableLayout.visibility = View.VISIBLE
            }

        }
    }

}