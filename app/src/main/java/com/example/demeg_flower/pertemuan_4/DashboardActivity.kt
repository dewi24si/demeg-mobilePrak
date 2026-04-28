package com.example.demeg_flower.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.demeg_flower.R
import com.example.demeg_flower.databinding.ActivityDashboardBinding
import com.example.demeg_flower.pertemuan_6.PrefHelper
import com.example.demeg_flower.pertemuan_6.WebViewActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

/**
 * Pertemuan 4 – DashboardActivity (diperbarui untuk Pertemuan 6)
 *
 * Perubahan Pertemuan 6:
 *   ✅ Toolbar dengan action button "Website Bina Desa"
 *   ✅ Logout menghapus SharedPreferences (isLogin = false)
 */
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    companion object {
        const val EXTRA_USERNAME   = "extra_username"
        const val EXTRA_PAGE_TITLE = "extra_page_title"
        const val EXTRA_PAGE_DESC  = "extra_page_desc"

        private const val TAG = "DashboardActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("onCreate", "DashboardActivity dibuat pertama kali")

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ── Pertemuan 6: Setup Toolbar ──
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.toolbar_dashboard_title)

        // Tampilkan sapaan username
        val username = intent.getStringExtra(EXTRA_USERNAME) ?: "Pengguna"
        binding.tvGreeting.text = "Halo, $username! 👋"

        setupListeners()
    }

    // Dashboard tidak perlu menu WebView di Toolbar
    // karena tombol Bina Desa sudah ada sebagai card di layout
    override fun onCreateOptionsMenu(menu: Menu): Boolean = false


    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: DashboardActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "DashboardActivity dihapus dari stack")
    }

    private fun setupListeners() {
        binding.btnBangunRuang.setOnClickListener {
            val intent = Intent(this, BangunRuangActivity::class.java)
            intent.putExtra(EXTRA_PAGE_TITLE, "Rumus Bangun Ruang")
            intent.putExtra(EXTRA_PAGE_DESC,  "Kumpulan rumus volume & luas permukaan bangun ruang tiga dimensi.")
            startActivity(intent)
        }

        binding.btnCustom1.setOnClickListener {
            val intent = Intent(this, CustomActivity1::class.java)
            intent.putExtra(EXTRA_PAGE_TITLE, "Bunga Anggrek")
            intent.putExtra(EXTRA_PAGE_DESC,  "Anggrek adalah salah satu keluarga tanaman berbunga terbesar dan paling beragam di dunia.")
            startActivity(intent)
        }

        binding.btnCustom2.setOnClickListener {
            val intent = Intent(this, CustomActivity2::class.java)
            intent.putExtra(EXTRA_PAGE_TITLE, "Bunga Mawar")
            intent.putExtra(EXTRA_PAGE_DESC,  "Mawar adalah simbol cinta dan keindahan yang dikenal di seluruh penjuru dunia.")
            startActivity(intent)
        }

        // ── Pertemuan 6: Tombol Website Bina Desa ──
        binding.btnBinaDesa.setOnClickListener {
            startActivity(Intent(this, com.example.demeg_flower.pertemuan_6.WebViewActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
            .setPositiveButton("Ya") { dialog, _ ->
                dialog.dismiss()
                Log.e("Info Dialog", "User memilih Logout – Ya!")

                // ── Pertemuan 6: Hapus status login dari SharedPreferences ──
                PrefHelper.clearLogin(this)

                val intent = Intent(this, com.example.demeg_flower.pertemuan_3.MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
                Log.e("Info Dialog", "User memilih Logout – Batal!")

                Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT)
                    .setAction("OK") {
                        Log.e("Info Snackbar", "Snackbar ditutup")
                    }
                    .show()
            }
            .show()
    }
}
