package com.venicio.venicioflix.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.venicio.venicioflix.data.model.Episode
import com.venicio.venicioflix.databinding.ItemEpisodesSeasonBinding

class RecyclerEpisodesAdapter(
     val listEpisodes: List<Episode>
) :
    RecyclerView.Adapter<RecyclerEpisodesAdapter.EpisodesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEpisodesSeasonBinding.inflate(inflater, parent, false)

        return EpisodesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bindEpisode(listEpisodes[position])
    }

    override fun getItemCount(): Int = listEpisodes.size

    class EpisodesViewHolder(val binding: ItemEpisodesSeasonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindEpisode(episodes: Episode) {
            binding.tvNameEpisode.text = episodes.name
        }


    }
}

