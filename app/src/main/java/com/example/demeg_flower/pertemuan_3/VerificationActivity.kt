package com.example.demeg_flower.pertemuan_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demeg_flower.databinding.ActivityVerificationBinding
import com.example.demeg_flower.pertemuan_6.PrefHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Quiz B – Soal 2 (30 Point): Verifikasi OTP
 * OTP valid = sama dengan No. HP yang diinputkan saat registrasi
 *
 * Quiz B – Soal 3 (25 Point): Save to SharedPreferences
 * Jika OTP valid → simpan data registrasi ke SharedPreferences, lalu kembali ke Login
 */
class VerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationBinding

    // Data dari RegisterActivity
    private var nama     = ""
    private var phone    = ""
    private var username = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari intent
        nama     = intent.getStringExtra("extra_nama")     ?: ""
        phone    = intent.getStringExtra("extra_phone")    ?: ""
        username = intent.getStringExtra("extra_username") ?: ""
        password = intent.getStringExtra("extra_password") ?: ""

        // Tampilkan hint nomor HP (3 digit pertama + ***)
        val maskedPhone = phone.take(3) + "*".repeat(maxOf(0, phone.length - 3))
        binding.tvOtpHint.text = "Kode OTP dikirim ke $maskedPhone"

        binding.btnVerify.setOnClickListener { handleVerify() }
        binding.tvBackToRegister.setOnClickListener { finish() }
    }
    // Kosong → tampilkan MaterialAlertDialog
    // Berbeda → tampilkan MaterialAlertDialog
    private fun handleVerify() {
        val otp = binding.etOtp.text.toString().trim()

        if (otp.isEmpty()) {
            showErrorDialog("Kode OTP tidak boleh kosong!")
            return
        }

        if (otp != phone) {
            showErrorDialog("Kode OTP salah! Pastikan kode sesuai dengan nomor handphone yang Anda daftarkan.")
            return
        }

        // ── Soal 3: OTP benar → simpan ke SharedPreferences ─────
        PrefHelper.saveRegisteredUser(this, nama, phone, username, password)

        // Navigasi kembali ke Login dan bersihkan back stack
        MaterialAlertDialogBuilder(this)
            .setTitle("Registrasi Berhasil 🎉")
            .setMessage("Akun Anda telah berhasil dibuat. Silakan login menggunakan username dan password yang telah didaftarkan.")
            .setPositiveButton("Login Sekarang") { _, _ ->
                val intent = Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
                startActivity(intent)
                finish()
            }
            .setCancelable(false)
            .show()
    }

    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Verifikasi Gagal")
            .setMessage(message)
            .setPositiveButton("Coba Lagi") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
