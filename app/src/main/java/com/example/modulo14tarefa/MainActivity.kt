package com.example.modulo14tarefa

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var drawer: DrawerLayout
    lateinit var navDrawer: NavigationView
    lateinit var bottomNav: BottomNavigationView
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    var currentPlay: String = "Pedra"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar2)

        drawer = binding.root
        navDrawer = binding.navView
        bottomNav = binding.bottomNav

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController


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

        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.playerFragment,R.id.resultFragment), drawer)
        setupActionBarWithNavController(navController, appBarConfiguration)
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
        navDrawer.setupWithNavController(navController)
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
        onPlaySelected(currentPlay)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun onPlaySelected(selectedPlay: String){
        currentPlay = selectedPlay
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val avaiablePlays = resources.getStringArray(R.array.avaiable_plays)
        currentPlay = avaiablePlays[position]
        Log.d("CADEOERRO", "chegou no mainactivity onitemselected: $currentPlay")

        Toast.makeText(this,"Jogada selecionada: $currentPlay", Toast.LENGTH_SHORT).show() // um teste pra ver se ta armazenando certo

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

}
