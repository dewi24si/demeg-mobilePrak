package com.example.demeg_flower.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demeg_flower.BaseActivity
import com.example.demeg_flower.databinding.ActivityMain3Binding
import com.example.demeg_flower.pertemuan_6.PrefHelper
import com.example.demeg_flower.utils.NotificationHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            handleLogin()
        }

        binding.tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Fitur reset password segera hadir 🔧", Toast.LENGTH_SHORT).show()
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun handleLogin() {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (username.isEmpty()) {
            binding.etUsername.error = getString(com.example.demeg_flower.R.string.error_username_empty)
            binding.etUsername.requestFocus()
            return
        }
        if (password.isEmpty()) {
            binding.etPassword.error = getString(com.example.demeg_flower.R.string.error_password_empty)
            binding.etPassword.requestFocus()
            return
        }
        if (password.length < 6) {
            binding.etPassword.error = getString(com.example.demeg_flower.R.string.error_password_short)
            binding.etPassword.requestFocus()
            return
        }

        val rule1 = username == password

        val regUsername = PrefHelper.getRegisteredUsername(this)
        val regPassword = PrefHelper.getRegisteredPassword(this)
        val rule2 = PrefHelper.hasRegisteredUser(this)
                && username == regUsername
                && password == regPassword

        if (!rule1 && !rule2) {
            binding.etPassword.error = "Username atau password salah"
            binding.etPassword.requestFocus()
            return
        }

        Toast.makeText(this, getString(com.example.demeg_flower.R.string.toast_login_success), Toast.LENGTH_SHORT).show()

        PrefHelper.setLogin(this, username)

        // Notifikasi selamat datang setelah login berhasil
        val notifIntent = Intent(this, BaseActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("extra_username", username)
        }
        NotificationHelper.showNotification(
            context = this,
            title = "Selamat Datang, $username! 👋",
            message = "Kamu berhasil masuk ke aplikasi Bina Desa. Tap untuk lihat dashboard.",
            intent = notifIntent
        )

        val intent = Intent(this, BaseActivity::class.java)
        intent.putExtra("extra_username", username)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
