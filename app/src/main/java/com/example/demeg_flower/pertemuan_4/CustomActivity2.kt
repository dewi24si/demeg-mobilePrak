package com.example.demeg_flower.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.demeg_flower.R
import com.example.demeg_flower.databinding.ActivityCustom2Binding
import com.example.demeg_flower.pertemuan_6.WebViewActivity

/**
 * Pertemuan 4 – CustomActivity2: Bunga Mawar (diperbarui untuk Pertemuan 6)
 *
 * Perubahan Pertemuan 6:
 *   ✅ Toolbar dengan tombol back dan action button "Website Bina Desa"
 *   ✅ Hapus btnBack lama (digantikan Toolbar)
 */
class CustomActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityCustom2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("onCreate", "CustomActivity2 dibuat pertama kali")

        binding = ActivityCustom2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // ── Pertemuan 6: Setup Toolbar ──
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val pageTitle = intent.getStringExtra(DashboardActivity.EXTRA_PAGE_TITLE) ?: "Bunga Mawar"
        val pageDesc  = intent.getStringExtra(DashboardActivity.EXTRA_PAGE_DESC)  ?: ""

        Log.e("Data Intent", "Judul: $pageTitle , Deskripsi: $pageDesc")

        supportActionBar?.title = pageTitle
        binding.tvPageTitle.text = pageTitle
        binding.tvPageDesc.text  = pageDesc
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_webview -> {
                startActivity(Intent(this, WebViewActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: CustomActivity2 terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "CustomActivity2 dihapus dari stack")
    }
}
