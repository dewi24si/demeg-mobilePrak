package com.example.demeg_flower.pertemuan_6

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.demeg_flower.R
import com.example.demeg_flower.databinding.ActivityWebViewBinding

/**
 * Pertemuan 6 – WebViewActivity
 * Menampilkan website Bina Desa di dalam aplikasi.
 * URL: http://demeg-adminpenggaduan.alwaysdata.net
 */
class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    companion object {
        const val URL_BINA_DESA = "http://demeg-adminpenggaduan.alwaysdata.net"
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.toolbar_webview_title)
            setDisplayHomeAsUpEnabled(true)
        }

        // Konfigurasi WebView
        with(binding.webView) {
            settings.javaScriptEnabled    = true
            settings.domStorageEnabled    = true
            settings.loadsImagesAutomatically = true
            settings.useWideViewPort      = true
            settings.loadWithOverviewMode = true

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean = false
            }

            loadUrl(URL_BINA_DESA)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @Deprecated("Deprecated in API 33 but needed for WebView back nav")
    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            @Suppress("DEPRECATION")
            super.onBackPressed()
        }
    }
}
