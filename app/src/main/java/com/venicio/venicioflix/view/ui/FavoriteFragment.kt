package com.venicio.venicioflix.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.venicio.venicioflix.databinding.FragmentFavoriteBinding



class FavoriteFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        return (binding.root)
    }

}