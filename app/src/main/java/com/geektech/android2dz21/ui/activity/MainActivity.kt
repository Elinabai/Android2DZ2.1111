package com.geektech.android2dz21.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.geektech.android2dz21.App
import com.geektech.android2dz21.R


import com.geektech.android2dz21.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.
        findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        if (App.preferenceHelper.saveBoolean && App.preferenceHelper.signUp ) {
                navController.navigate(R.id.signInFragment)
            }else if (App.preferenceHelper.saveBoolean){
               navController.navigate(R.id.noteFragment)
        }else{
            navController.navigate(R.id.onBoardFragment)
        }
    }
}