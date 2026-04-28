package com.example.demeg_flower.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demeg_flower.databinding.ActivityWelcomeBinding

/**
 * Pertemuan 3 - Welcome Activity
 * Menerima data username dari Intent yang dikirim LoginActivity
 */
class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil username dari Intent
        val username = intent.getStringExtra(EXTRA_USERNAME) ?: "Pengguna"
        binding.tvWelcomeUsername.text = "Halo, $username! 👋"

        setupListeners()
    }

    private fun setupListeners() {

        binding.btnExplore.setOnClickListener {
            Toast.makeText(this, "Selamat menjelajahi DemegApp! 🚀", Toast.LENGTH_SHORT).show()
        }

        // Tombol Keluar → kembali ke LoginActivity
        binding.tvLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
