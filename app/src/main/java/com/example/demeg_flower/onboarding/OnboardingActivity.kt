package com.example.demeg_flower.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.demeg_flower.databinding.ActivityOnboardingBinding
import com.example.demeg_flower.pertemuan_6.PrefHelper

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = OnboardingAdapter(this)
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)

        // Update tampilan tombol sesuai halaman aktif
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == adapter.itemCount - 1) {
                    // Halaman terakhir → tampilkan "Ayo Mulai"
                    binding.btnAyoMulai.visibility = View.VISIBLE
                    binding.btnNext.visibility = View.GONE
                } else {
                    binding.btnAyoMulai.visibility = View.GONE
                    binding.btnNext.visibility = View.VISIBLE
                }
            }
        })

        // Tombol Next
        binding.btnNext.setOnClickListener {
            val current = binding.viewPager.currentItem
            binding.viewPager.currentItem = current + 1
        }

        // Tombol Ayo Mulai → simpan flag, ke Login
        binding.btnAyoMulai.setOnClickListener {
            PrefHelper.setOnboardingDone(this)
            val intent = Intent(this, com.example.demeg_flower.pertemuan_3.MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Tombol Skip
        binding.btnSkip.setOnClickListener {
            PrefHelper.setOnboardingDone(this)
            val intent = Intent(this, com.example.demeg_flower.pertemuan_3.MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
