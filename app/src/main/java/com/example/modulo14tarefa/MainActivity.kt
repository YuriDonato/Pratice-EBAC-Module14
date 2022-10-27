package com.example.modulo14tarefa

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.modulo14tarefa.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(),PlayerFragment.JogadorListener {
    lateinit var drawer: DrawerLayout
    lateinit var navDrawer: NavigationView
    lateinit var bottomNav: BottomNavigationView
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var currentPlay: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        currentPlay = "Pedra"

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar2)

        drawer = binding.root
        navDrawer = binding.navView
        bottomNav = binding.bottomNav

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.playerFragment,R.id.resultFragment), drawer)

        navController.addOnDestinationChangedListener{_,destination,_ ->
            when(destination.id){
                R.id.homeFragment -> {
                    bottomNav.visibility = View.GONE
                }

                else ->{
                    bottomNav.visibility = View.VISIBLE
                }
            }
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navDrawer.setupWithNavController(navController)
        bottomNav.setupWithNavController(navController)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.resultFragment -> {
                    val args = Bundle()
                    args.putString("currentPlay",currentPlay)
                    navController.navigate(it.itemId, args)
                }
                else -> navController.navigate(it.itemId)
            }
            true
        }
        navDrawer.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.resultFragment -> {
                    val args = Bundle()
                    args.putString("currentPlay",currentPlay)
                    navController.navigate(it.itemId, args)
                }
                else -> navController.navigate(it.itemId)
            }
            true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Armazenar aqui as informacoes
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onPlaySelected(selectedPlay: String) {
        currentPlay = selectedPlay
    }

}
