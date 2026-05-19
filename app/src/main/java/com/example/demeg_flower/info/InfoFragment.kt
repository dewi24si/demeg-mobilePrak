package com.example.demeg_flower.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.demeg_flower.R
import com.example.demeg_flower.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private val infoList = listOf(
        InfoModel("Pak Joko Susanto", "Kepala Desa", "https://avatar.iran.liara.run/public/1"),
        InfoModel("Bu Sari Wulandari", "Sekretaris Desa", "https://avatar.iran.liara.run/public/2"),
        InfoModel("Pak Budi Hartono", "Bendahara Desa", "https://avatar.iran.liara.run/public/3"),
        InfoModel("Bu Rina Kusuma", "Ketua PKK", "https://avatar.iran.liara.run/public/4"),
        InfoModel("Pak Agus Setiawan", "Ketua RT 01", "https://avatar.iran.liara.run/public/5"),
        InfoModel("Bu Dewi Anggraini", "Ketua RT 02", "https://avatar.iran.liara.run/public/6"),
        InfoModel("Pak Hendra Wijaya", "Ketua RT 03", "https://avatar.iran.liara.run/public/7"),
        InfoModel("Bu Ani Rahayu", "Ketua Posyandu", "https://avatar.iran.liara.run/public/8"),
        InfoModel("Pak Dodi Santoso", "Ketua Karang Taruna", "https://avatar.iran.liara.run/public/9"),
        InfoModel("Bu Fitri Handayani", "Kader Kesehatan", "https://avatar.iran.liara.run/public/10")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.toolbar_info_title)
        }

        // Custom Adapter
        val adapter = InfoAdapter(requireContext(), infoList)
        binding.listInfoItems.adapter = adapter

        // onClick item
        binding.listInfoItems.setOnItemClickListener { _, _, position, _ ->
            val item = infoList[position]
            com.google.android.material.snackbar.Snackbar.make(
                binding.root,
                "Info: ${item.namaWarga} (${item.keterangan})",
                com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
