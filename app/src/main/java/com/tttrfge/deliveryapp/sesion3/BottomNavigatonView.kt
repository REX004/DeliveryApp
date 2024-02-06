package com.tttrfge.deliveryapp.sesion3

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tttrfge.deliveryapp.R
import com.tttrfge.deliveryapp.databinding.BottomNavigationBinding
import com.tttrfge.deliveryapp.sesion3.fragments.homepac.Home
import com.tttrfge.deliveryapp.sesion3.fragments.profilepac.Profile
import com.tttrfge.deliveryapp.sesion3.fragments.trackpacage.Track
import com.tttrfge.deliveryapp.sesion3.fragments.walletpacage.Wallet

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: BottomNavigationBinding
    var isChecked = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        binding = BottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        val navController = navHostFragment.navController

        findViewById<BottomNavigationView>(R.id.nav_view)
            .setupWithNavController(navController)
    }
}