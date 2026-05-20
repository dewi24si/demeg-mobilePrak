package com.example.demeg_flower.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.demeg_flower.R
import com.example.demeg_flower.databinding.FragmentHomeBinding
import com.example.demeg_flower.pertemuan_4.BangunRuangActivity
import com.example.demeg_flower.pertemuan_4.CustomActivity1
import com.example.demeg_flower.pertemuan_4.CustomActivity2
import com.example.demeg_flower.pertemuan_6.PrefHelper
import com.example.demeg_flower.pertemuan_6.WebViewActivity
import com.example.demeg_flower.pertemuan_9.NinthActivity
import com.example.demeg_flower.pertemuan_10.TenthActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var username: String = "Pengguna"

    companion object {
        private const val ARG_USERNAME = "arg_username"

        fun newInstance(username: String): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_USERNAME, username)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        username = arguments?.getString(ARG_USERNAME) ?: "Pengguna"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.toolbar_dashboard_title)
        }

        // Tampilkan greeting
        binding.tvGreeting.text = "Halo, $username! 👋"

        // Tombol Bangun Ruang
        binding.btnBangunRuang.setOnClickListener {
            val intent = Intent(requireContext(), BangunRuangActivity::class.java)
            intent.putExtra("extra_page_title", "Rumus Bangun Ruang")
            intent.putExtra("extra_page_desc", "Kumpulan rumus volume & luas permukaan bangun ruang tiga dimensi.")
            startActivity(intent)
        }

        // Tombol Custom 1 – Bunga Anggrek
        binding.btnCustom1.setOnClickListener {
            val intent = Intent(requireContext(), CustomActivity1::class.java)
            intent.putExtra("extra_page_title", "Bunga Anggrek")
            intent.putExtra("extra_page_desc", "Anggrek adalah salah satu keluarga tanaman berbunga terbesar dan paling beragam di dunia.")
            startActivity(intent)
        }

        // Tombol Custom 2 – Bunga Mawar
        binding.btnCustom2.setOnClickListener {
            val intent = Intent(requireContext(), CustomActivity2::class.java)
            intent.putExtra("extra_page_title", "Bunga Mawar")
            intent.putExtra("extra_page_desc", "Mawar adalah simbol cinta dan keindahan yang dikenal di seluruh penjuru dunia.")
            startActivity(intent)
        }

        // Tombol Website Bina Desa
        binding.btnBinaDesa.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // Tombol Pertemuan 9 – Filter Pengaduan
        binding.btnPertemuan9.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }

        // Tombol Pertemuan 10 – TabLayout & RecyclerView
        binding.btnPertemuan10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }

        // Tombol Logout
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
            .setPositiveButton("Ya") { dialog, _ ->
                dialog.dismiss()
                PrefHelper.clearLogin(requireContext())
                val intent = Intent(requireContext(), com.example.demeg_flower.pertemuan_3.MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
                Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT)
                    .setAction("OK") {}
                    .show()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
