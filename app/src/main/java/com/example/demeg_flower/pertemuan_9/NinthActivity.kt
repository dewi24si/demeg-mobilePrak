package com.example.demeg_flower.pertemuan_9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demeg_flower.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar + tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Filter Pengaduan"
            setDisplayHomeAsUpEnabled(true)
        }

        // ChipGroup listener
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(this, "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // Button Cari
        binding.btnCari.setOnClickListener {
            val query = binding.etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                Toast.makeText(this, "Mencari: $query", Toast.LENGTH_SHORT).show()
            } else {
                binding.textInputLayout.error = "Masukkan kata kunci pencarian"
            }
        }

        // Reset error saat user mulai ketik
        binding.etSearch.setOnFocusChangeListener { _, _ ->
            binding.textInputLayout.error = null
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
