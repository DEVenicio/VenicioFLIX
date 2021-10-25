package com.venicio.venicioflix.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.tabs.TabLayout
import com.venicio.venicioflix.R
import com.venicio.venicioflix.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController
    lateinit var allTabs: TabLayout
    lateinit var seriesFrag: SeriesFragment
    lateinit var moviesFrag: MoviesFragment
    lateinit var favoriteFrag: FavoriteFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        allTabs = binding.tabLayout
        bindWidgetsWithAnEvent()
        setupTabLayout()

        setSupportActionBar(binding.toolbar)

        navController = findNavController(R.id.containerRoot)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController,appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun bindWidgetsWithAnEvent() {

        allTabs.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                setCurrentTabFragment(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    fun setCurrentTabFragment(tabPosition: Int) {
        when (tabPosition) {
            0 -> findNavController(R.id.containerRoot).navigate(R.id.moviesFragment)
            1 -> findNavController(R.id.containerRoot).navigate(R.id.seriesFragment)
            2 -> findNavController(R.id.containerRoot).navigate(R.id.favoriteFragment)
        }
    }

    fun setupTabLayout() {
        seriesFrag = SeriesFragment()
        moviesFrag = MoviesFragment()
        favoriteFrag = FavoriteFragment()

        allTabs.addTab(allTabs.newTab().setText("FILMES"))
        allTabs.addTab(allTabs.newTab().setText("SÉRIES"), true)
        allTabs.addTab(allTabs.newTab().setText("FAVORITOS"))
    }

}
