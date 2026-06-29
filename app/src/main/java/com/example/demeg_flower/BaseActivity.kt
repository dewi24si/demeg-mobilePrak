package com.example.demeg_flower

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.demeg_flower.Home.HomeFragment
import com.example.demeg_flower.about.AboutFragment
import com.example.demeg_flower.databinding.ActivityBaseBinding
import com.example.demeg_flower.info.InfoFragment
import com.example.demeg_flower.pengumuman.PengumumanFragment
import com.example.demeg_flower.profile.ProfileFragment
import com.example.demeg_flower.utils.PermissionHelper

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak, beberapa fitur tidak akan berfungsi", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
            }
        }

        val username = intent.getStringExtra("extra_username") ?: "Pengguna"
        val navigateTo = intent.getStringExtra("navigate_to")

        val startFragment: Fragment = when (navigateTo) {
            "info" -> {
                binding.bottomNavView.selectedItemId = R.id.info
                InfoFragment()
            }
            "pengumuman" -> {
                binding.bottomNavView.selectedItemId = R.id.pengumuman
                PengumumanFragment()
            }
            else -> {
                binding.bottomNavView.selectedItemId = R.id.home
                HomeFragment.newInstance(username)
            }
        }

        replaceFragment(startFragment)

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> { replaceFragment(HomeFragment.newInstance(username)); true }
                R.id.about -> { replaceFragment(AboutFragment()); true }
                R.id.profile -> { replaceFragment(ProfileFragment()); true }
                R.id.info -> { replaceFragment(InfoFragment()); true }
                R.id.pengumuman -> { replaceFragment(PengumumanFragment()); true }
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