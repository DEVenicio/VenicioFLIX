package com.venicio.venicioflix.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.venicio.venicioflix.databinding.FragmentMoviesBinding


class MoviesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)

        return (binding.root)
    }

}