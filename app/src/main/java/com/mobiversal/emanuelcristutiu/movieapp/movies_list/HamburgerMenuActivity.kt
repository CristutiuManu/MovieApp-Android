package com.mobiversal.emanuelcristutiu.movieapp.movies_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mobiversal.emanuelcristutiu.movieapp.PreferencesActivity
import com.mobiversal.emanuelcristutiu.movieapp.R
import com.mobiversal.emanuelcristutiu.movieapp.ui.search.SearchFragment
import kotlinx.android.synthetic.main.app_bar_main.*

class HamburgerMenuActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hamburger_menu)

        fab.setOnClickListener{
            startActivity(Intent(this, PreferencesActivity::class.java))
        }
         
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_search,
                R.id.nav_save
            ), drawerLayout
        )


        navView.setupWithNavController(navController)

        // Bar Search
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.bringToFront()
        navController.addOnDestinationChangedListener{controller, destination, arguments ->
            if(destination.id == R.id.nav_save){
                Log.d("ceva", "test frag")
                searchView?.visibility = View.GONE
            } else {
                searchView?.visibility = View.VISIBLE
            }
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextSubmit(p0: String?): Boolean {
                getForegroundFragment()?.let { currentFragment ->
                    if (currentFragment is SearchFragment) {
                        currentFragment.search(searchView.query.toString())
                    }
                }
                return false
            }
        })

    }

    fun getForegroundFragment(): Fragment? {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.hamburger_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}