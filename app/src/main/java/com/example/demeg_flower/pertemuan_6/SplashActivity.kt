package com.example.demeg_flower.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.demeg_flower.BaseActivity
import com.example.demeg_flower.databinding.ActivitySplashBinding
import com.example.demeg_flower.onboarding.OnboardingActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    companion object {
        private const val SPLASH_DELAY_MS = 2500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            navigateFromSplash()
        }, SPLASH_DELAY_MS)
    }

    private fun navigateFromSplash() {
        val intent = when {
            // Belum pernah onboarding → tampilkan onboarding dulu
            !PrefHelper.isOnboardingDone(this) -> {
                Intent(this, OnboardingActivity::class.java)
            }
            // Sudah onboarding & sudah login → ke Home
            PrefHelper.isLogin(this) -> {
                Intent(this, BaseActivity::class.java).apply {
                    putExtra("extra_username", PrefHelper.getUsername(this@SplashActivity))
                }
            }
            // Sudah onboarding, belum login → ke Login
            else -> {
                Intent(this, com.example.demeg_flower.pertemuan_3.MainActivity::class.java)
            }
        }

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
