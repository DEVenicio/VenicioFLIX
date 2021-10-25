package com.venicio.venicioflix.di
import com.venicio.venicioflix.data.repository.Repository
import com.venicio.venicioflix.view.ui.SeriesDetailsFragmentArgs
import com.venicio.venicioflix.viewmodel.DetailsViewModel
import com.venicio.venicioflix.viewmodel.SeriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { (rp: Repository) ->  SeriesViewModel(rp) }
    viewModel { (rp: Repository, arguments: SeriesDetailsFragmentArgs) -> DetailsViewModel(rp,arguments) }
}