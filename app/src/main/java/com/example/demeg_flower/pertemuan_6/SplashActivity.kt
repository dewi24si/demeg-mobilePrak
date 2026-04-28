package com.example.demeg_flower.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.demeg_flower.databinding.ActivitySplashBinding
import com.example.demeg_flower.pertemuan_3.MainActivity
import com.example.demeg_flower.pertemuan_4.DashboardActivity

/**
 * Pertemuan 6 – SplashActivity
 * Launcher utama aplikasi Bina Desa.
 *
 * Alur:
 *   isLogin = true  → langsung ke DashboardActivity
 *   isLogin = false → ke LoginActivity (pertemuan_3/MainActivity)
 */
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
        val intent = if (PrefHelper.isLogin(this)) {
            // Sudah login → langsung ke Dashboard
            Intent(this, DashboardActivity::class.java).apply {
                putExtra(DashboardActivity.EXTRA_USERNAME, PrefHelper.getUsername(this@SplashActivity))
            }
        } else {
            // Belum login → ke halaman Login
            Intent(this, MainActivity::class.java)
        }

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
