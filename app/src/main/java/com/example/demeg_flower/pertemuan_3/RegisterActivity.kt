package com.example.demeg_flower.pertemuan_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demeg_flower.databinding.ActivityRegisterBinding

/**
 * Quiz B – Soal 1 (45 Point): Halaman Registrasi
 * Input: Nama, No. HP (inputNumber), Username, Password
 * Setelah submit → navigasi ke VerificationActivity
 */
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener { handleRegister() }
        binding.tvBackToLogin.setOnClickListener { finish() }
    }

    private fun handleRegister() {
        val nama     = binding.etNama.text.toString().trim()
        val phone    = binding.etPhone.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        // Validasi
        if (nama.isEmpty()) {
            binding.etNama.error = "Nama tidak boleh kosong"
            binding.etNama.requestFocus(); return
        }
        if (phone.isEmpty()) {
            binding.etPhone.error = "No. Handphone tidak boleh kosong"
            binding.etPhone.requestFocus(); return
        }
        if (phone.length < 8) {
            binding.etPhone.error = "No. Handphone minimal 8 digit"
            binding.etPhone.requestFocus(); return
        }
        if (username.isEmpty()) {
            binding.etUsername.error = "Username tidak boleh kosong"
            binding.etUsername.requestFocus(); return
        }
        if (password.isEmpty()) {
            binding.etPassword.error = "Password tidak boleh kosong"
            binding.etPassword.requestFocus(); return
        }
        if (password.length < 6) {
            binding.etPassword.error = "Password minimal 6 karakter"
            binding.etPassword.requestFocus(); return
        }

        // Semua valid → navigasi ke VerificationActivity
        val intent = Intent(this, VerificationActivity::class.java).apply {
            putExtra("extra_nama",     nama)
            putExtra("extra_phone",    phone)
            putExtra("extra_username", username)
            putExtra("extra_password", password)
        }
        startActivity(intent)
    }
}
