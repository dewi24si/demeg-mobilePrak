package com.example.demeg_flower.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demeg_flower.R
import com.example.demeg_flower.data.AppDatabase
import com.example.demeg_flower.data.entity.WargaEntity
import com.example.demeg_flower.databinding.FragmentInfoBinding
import kotlinx.coroutines.launch

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: WargaAdapter
    private lateinit var db: AppDatabase
    private val wargaList = mutableListOf<WargaEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.toolbar_info_title)
        }

        // Init DB & Adapter
        db = AppDatabase.getInstance(requireContext())
        adapter = WargaAdapter(wargaList, this)

        binding.rvWarga.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWarga.adapter = adapter
        binding.rvWarga.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )

        // FAB -> WargaFormActivity
        binding.fabAddWarga.setOnClickListener {
            startActivity(
                android.content.Intent(requireContext(), WargaFormActivity::class.java)
            )
        }

        fetchWarga()
    }

    private fun fetchWarga() {
        lifecycleScope.launch {
            val data = db.wargaDao().getAll()
            wargaList.clear()
            wargaList.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchWarga()
    }

    fun deleteWarga(warga: WargaEntity) {
        lifecycleScope.launch {
            db.wargaDao().delete(warga)
            fetchWarga()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
