package com.mobiversal.emanuelcristutiu.movieapp.actors_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiversal.emanuelcristutiu.movieapp.PreferencesActivity
import com.mobiversal.emanuelcristutiu.movieapp.R
import com.mobiversal.emanuelcristutiu.movieapp.actor.ActorsRepository
import com.mobiversal.emanuelcristutiu.movieapp.actor.FavoriteActor
import kotlinx.android.synthetic.main.activity_actors.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActorsActivity : AppCompatActivity() {

    private val actorsRepository = ActorsRepository.instance
    private var actors: List<FavoriteActor> = emptyList()
    private var searchQuery : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors)
        getActors()
        saveActorsButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                actorsRepository.deleteAll()
                actorsRepository.saveAll(getSelectedActors())
            }
            startActivity(Intent(this, PreferencesActivity::class.java))
        }

        search_actors.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(search_actors.query.toString())
                return false
            }
        })

        search_button.setOnClickListener{
            search_actors.isVisible = !search_actors.isVisible
        }

    }

    companion object {
        private val TAG = ActorsActivity::class.java.simpleName
    }

    private fun getActors(): List<FavoriteActor>? {
        GlobalScope.launch(Dispatchers.IO) {
            actors = actorsRepository.getAllRemote()
            actors.let { actors ->
                withContext(Dispatchers.Main) {
                    Log.d(TAG, actors.firstOrNull()?.name ?: "not found")
                    this@ActorsActivity.actors = actors
                    insertPreselectedItems()
                    setupRecyclerView()
                }
            }
        }
        return actors
    }

    private fun setupRecyclerView() {
        val adapter = ActorsAdapter(actors)
        val llm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_actors.layoutManager = llm
        rv_actors.adapter = adapter
    }

    private fun getSelectedActors(): List<FavoriteActor> {
        return actors.filter { actor -> actor.isSelected }
    }

    private fun insertPreselectedItems() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedActors: List<FavoriteActor> = actorsRepository.getAll()
            actors.forEach{ actor -> actor.isSelected= savedActors.contains(actor)}
        }
    }

    private fun search(query: String) {
        Log.d(TAG, "Search query is: $query")
        GlobalScope.launch  (Dispatchers.IO) {
            actors = actorsRepository.searchActorsRemote(query)
            withContext(Dispatchers.Main) {
                setupRecyclerView()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if (searchQuery.isNullOrBlank()) {
            getActors()
        }else search(searchQuery!!)

    }

}