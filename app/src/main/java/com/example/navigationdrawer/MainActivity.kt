package com.example.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.navigationdrawer.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    private lateinit var FraagmentManager: FragmentManager
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this,binding.drawerLayout, binding.toolbar, R.string.nav_open , R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
// hare have daut for navigationDrawer
        binding.navigationDrawer.setNavigationItemSelectedListener (this)

        binding.bottomNavigation.background =null
        binding.bottomNavigation.setOnItemSelectedListener { item->
           when(item.itemId){
               R.id.bottom_home -> openFragment(HomeFragment())
               R.id.bottom_search -> openFragment(ExploreFragment())
               R.id.bottom_add -> openFragment(FavoriteFragment())
               R.id.bottom_reels -> openFragment(ChatFragment())
               R.id.bottom_profile -> openFragment(UserFragment())
           }
            true
        }
        FraagmentManager = supportFragmentManager
        openFragment(HomeFragment())
    }
  // add fragment and nav_drawer id //
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
         R.id.nav_prime-> openFragment(InviteFragment())
         R.id.nave_wallet-> openFragment(walletFragment())
         R.id.nave_list-> openFragment(listFragment())
         R.id.nave_traval-> openFragment(TravalFragment())
         R.id.nave_logout-> openFragment(LogOut())
       }
      binding.drawerLayout.closeDrawer(GravityCompat.START)
      return true
    }
// bottom drawer code its open or close
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.getOnBackPressedDispatcher().onBackPressed()
        }
        super.onBackPressed()
    }
//Fragment open section code
    private fun openFragment(fragment:Fragment){
        val fragmentTransaction:FragmentTransaction = FraagmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.fragment_container,fragment)
    fragmentTransaction.commit()
    }

}