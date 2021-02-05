package com.mobiversal.emanuelcristutiu.movieapp.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mobiversal.emanuelcristutiu.movieapp.R

class SaveFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_save, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val savedMoviesTabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        val savedMoviesViewPager: ViewPager = view.findViewById(R.id.viewPager)
        val adapter: SavedMoviesPagerAdapter = SavedMoviesPagerAdapter(view.context, childFragmentManager)
        savedMoviesViewPager.adapter = adapter
        savedMoviesTabLayout.setupWithViewPager(savedMoviesViewPager)

    }
}