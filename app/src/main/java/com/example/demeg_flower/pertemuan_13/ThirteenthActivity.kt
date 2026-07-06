package com.example.demeg_flower.pertemuan_13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demeg_flower.databinding.ActivityThirteenthBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Pertemuan 13 – ThirteenthActivity
 * TabLayout + ViewPager2 dengan 3 Fragment:
 *   1. TabCaptureFragment  -> Camera Capture (simpan ke galeri)
 *   2. TabQrcodeFragment   -> Generate QR Code (ZXing)
 *   3. TabScanFragment     -> Scan QR Code real-time (CameraX + ML Kit)
 */
class ThirteenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirteenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        val tabsAdapter = ThirteenthTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Capture"
                1 -> "QR Code"
                2 -> "Scan"
                else -> ""
            }
        }.attach()
    }
}
