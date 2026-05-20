package com.example.demeg_flower.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demeg_flower.databinding.FragmentTabCBinding

class TabCFragment : Fragment() {

    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    private val complaintList = listOf(
        ComplaintModel("Jalan Berlubang Rt.03", "Infrastruktur", "https://picsum.photos/seed/road1/400/300"),
        ComplaintModel("Sampah Menumpuk di TPS", "Kebersihan", "https://picsum.photos/seed/trash1/400/300"),
        ComplaintModel("Lampu Jalan Mati", "Fasilitas Umum", "https://picsum.photos/seed/lamp1/400/300"),
        ComplaintModel("Drainase Tersumbat", "Infrastruktur", "https://picsum.photos/seed/drain1/400/300"),
        ComplaintModel("Pohon Tumbang Blok B", "Lingkungan", "https://picsum.photos/seed/tree1/400/300"),
        ComplaintModel("Air PDAM Mati 3 Hari", "Air & Sanitasi", "https://picsum.photos/seed/water1/400/300"),
        ComplaintModel("Jembatan Retak Rt.07", "Infrastruktur", "https://picsum.photos/seed/bridge1/400/300"),
        ComplaintModel("Tempat Sampah Rusak", "Kebersihan", "https://picsum.photos/seed/bin1/400/300"),
        ComplaintModel("Genangan Air Hujan", "Infrastruktur", "https://picsum.photos/seed/flood1/400/300"),
        ComplaintModel("Pos Keamanan Vandal", "Ketertiban", "https://picsum.photos/seed/post1/400/300"),
        ComplaintModel("Taman Desa Tidak Terawat", "Lingkungan", "https://picsum.photos/seed/park1/400/300"),
        ComplaintModel("Fasilitas Balai Desa Rusak", "Fasilitas Umum", "https://picsum.photos/seed/hall1/400/300"),
        ComplaintModel("Saluran Irigasi Bocor", "Air & Sanitasi", "https://picsum.photos/seed/irrig1/400/300"),
        ComplaintModel("MCK Umum Kotor", "Air & Sanitasi", "https://picsum.photos/seed/toilet1/400/300"),
        ComplaintModel("Kabel Listrik Menjuntai", "Fasilitas Umum", "https://picsum.photos/seed/cable1/400/300"),
        ComplaintModel("Papan Nama Jalan Hilang", "Infrastruktur", "https://picsum.photos/seed/sign1/400/300"),
        ComplaintModel("Pedagang Kaki Lima Liar", "Ketertiban", "https://picsum.photos/seed/vendor1/400/300"),
        ComplaintModel("Kebisingan Malam Hari", "Ketertiban", "https://picsum.photos/seed/night1/400/300"),
        ComplaintModel("Limbah Industri Rumahan", "Lingkungan", "https://picsum.photos/seed/waste1/400/300"),
        ComplaintModel("Kebakaran Lahan Kosong", "Lingkungan", "https://picsum.photos/seed/fire1/400/300"),
        ComplaintModel("Aspal Mengelupas Rt.12", "Infrastruktur", "https://picsum.photos/seed/asphalt1/400/300"),
        ComplaintModel("Sumur Warga Kering", "Air & Sanitasi", "https://picsum.photos/seed/well1/400/300"),
        ComplaintModel("Pagar Posyandu Roboh", "Fasilitas Umum", "https://picsum.photos/seed/fence1/400/300"),
        ComplaintModel("Banjir Rob Wilayah Timur", "Infrastruktur", "https://picsum.photos/seed/banjir1/400/300"),
        ComplaintModel("Hewan Liar Masuk Kampung", "Lingkungan", "https://picsum.photos/seed/animal1/400/300"),
        ComplaintModel("Trotoar Rusak Jl. Melati", "Infrastruktur", "https://picsum.photos/seed/sidewalk1/400/300"),
        ComplaintModel("Pembuangan Sampah Ilegal", "Kebersihan", "https://picsum.photos/seed/dump1/400/300"),
        ComplaintModel("Wifi Publik Tidak Aktif", "Fasilitas Umum", "https://picsum.photos/seed/wifi1/400/300"),
        ComplaintModel("Peternakan Tidak Berizin", "Ketertiban", "https://picsum.photos/seed/farm1/400/300"),
        ComplaintModel("Gorong-gorong Tersumbat", "Infrastruktur", "https://picsum.photos/seed/sewer1/400/300"),
        ComplaintModel("Lapangan Olahraga Rusak", "Fasilitas Umum", "https://picsum.photos/seed/field1/400/300"),
        ComplaintModel("Polusi Asap Pembakaran", "Lingkungan", "https://picsum.photos/seed/smoke1/400/300"),
        ComplaintModel("Atap SD Desa Bocor", "Fasilitas Umum", "https://picsum.photos/seed/school1/400/300"),
        ComplaintModel("Kios Desa Tidak Terawat", "Fasilitas Umum", "https://picsum.photos/seed/kiosk1/400/300"),
        ComplaintModel("Vandalisme Tembok Desa", "Ketertiban", "https://picsum.photos/seed/graffiti1/400/300"),
        ComplaintModel("Jalan Licin Tanpa Rambu", "Infrastruktur", "https://picsum.photos/seed/slippery1/400/300"),
        ComplaintModel("Bangkai Hewan di Sungai", "Lingkungan", "https://picsum.photos/seed/river1/400/300"),
        ComplaintModel("Tangki Septik Penuh", "Air & Sanitasi", "https://picsum.photos/seed/septic1/400/300"),
        ComplaintModel("Pompa Air Desa Rusak", "Air & Sanitasi", "https://picsum.photos/seed/pump1/400/300"),
        ComplaintModel("Jembatan Kayu Patah", "Infrastruktur", "https://picsum.photos/seed/woodbridge1/400/300"),
        ComplaintModel("Penggalian Liar Tanah Desa", "Ketertiban", "https://picsum.photos/seed/dig1/400/300"),
        ComplaintModel("Puskesmas Kekurangan Obat", "Kesehatan", "https://picsum.photos/seed/clinic1/400/300"),
        ComplaintModel("Ventilasi Gedung PKK Buruk", "Fasilitas Umum", "https://picsum.photos/seed/vent1/400/300"),
        ComplaintModel("Jalur Evakuasi Banjir", "Infrastruktur", "https://picsum.photos/seed/evac1/400/300"),
        ComplaintModel("Pemasangan Listrik Ilegal", "Fasilitas Umum", "https://picsum.photos/seed/electric1/400/300"),
        ComplaintModel("Kurangnya Tempat Bermain", "Fasilitas Umum", "https://picsum.photos/seed/play1/400/300"),
        ComplaintModel("Sungai Tercemar Limbah", "Lingkungan", "https://picsum.photos/seed/pollute1/400/300"),
        ComplaintModel("Penambangan Pasir Liar", "Ketertiban", "https://picsum.photos/seed/mine1/400/300"),
        ComplaintModel("Kondisi Pasar Desa Kumuh", "Kebersihan", "https://picsum.photos/seed/market1/400/300"),
        ComplaintModel("Masjid Desa Perlu Renovasi", "Fasilitas Umum", "https://picsum.photos/seed/mosque1/400/300")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ComplaintAdapter(complaintList) { selectedItem ->
            Toast.makeText(
                requireContext(),
                "Laporan: ${selectedItem.title}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvComplaints.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
