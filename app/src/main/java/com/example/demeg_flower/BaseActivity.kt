package com.example.demeg_flower

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.demeg_flower.Home.HomeFragment
import com.example.demeg_flower.about.AboutFragment
import com.example.demeg_flower.databinding.ActivityBaseBinding
import com.example.demeg_flower.info.InfoFragment
import com.example.demeg_flower.pengumuman.PengumumanFragment
import com.example.demeg_flower.profile.ProfileFragment

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("extra_username") ?: "Pengguna"

        replaceFragment(HomeFragment.newInstance(username))

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment.newInstance(username))
                    true
                }
                R.id.about -> {
                    replaceFragment(AboutFragment())
                    true
                }
                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                R.id.info -> {
                    replaceFragment(InfoFragment())
                    true
                }
                R.id.pengumuman -> {
                    replaceFragment(PengumumanFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}
