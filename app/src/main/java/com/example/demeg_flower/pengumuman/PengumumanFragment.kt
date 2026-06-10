package com.example.demeg_flower.pengumuman

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demeg_flower.data.AppDatabase
import com.example.demeg_flower.data.entity.PengumumanEntity
import com.example.demeg_flower.databinding.FragmentPengumumanBinding
import kotlinx.coroutines.launch

class PengumumanFragment : Fragment() {

    private var _binding: FragmentPengumumanBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PengumumanAdapter
    private lateinit var db: AppDatabase
    private val pengumumanList = mutableListOf<PengumumanEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPengumumanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Pengumuman"
        }

        // Init DB & Adapter
        db = AppDatabase.getInstance(requireContext())
        adapter = PengumumanAdapter(pengumumanList, this)

        binding.rvPengumuman.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPengumuman.adapter = adapter
        binding.rvPengumuman.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )

        // FAB -> PengumumanFormActivity
        binding.fabAddPengumuman.setOnClickListener {
            startActivity(Intent(requireContext(), PengumumanFormActivity::class.java))
        }

        fetchPengumuman()
    }

    private fun fetchPengumuman() {
        lifecycleScope.launch {
            val data = db.pengumumanDao().getAll()
            pengumumanList.clear()
            pengumumanList.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchPengumuman()
    }

    fun deletePengumuman(item: PengumumanEntity) {
        lifecycleScope.launch {
            db.pengumumanDao().delete(item)
            fetchPengumuman()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
