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
        ComplaintModel("Jalan Berlubang Rt.03", "Infrastruktur", "https://loremflickr.com/400/300/road,pothole"),
        ComplaintModel("Sampah Menumpuk di TPS", "Kebersihan", "https://loremflickr.com/400/300/garbage,waste"),
        ComplaintModel("Lampu Jalan Mati", "Fasilitas Umum", "https://loremflickr.com/400/300/street,lamp,dark"),
        ComplaintModel("Drainase Tersumbat", "Infrastruktur", "https://loremflickr.com/400/300/drain,flood"),
        ComplaintModel("Pohon Tumbang Blok B", "Lingkungan", "https://loremflickr.com/400/300/fallen,tree"),
        ComplaintModel("Air PDAM Mati 3 Hari", "Air & Sanitasi", "https://loremflickr.com/400/300/water,pipe"),
        ComplaintModel("Jembatan Retak Rt.07", "Infrastruktur", "https://loremflickr.com/400/300/bridge,crack"),
        ComplaintModel("Tempat Sampah Rusak", "Kebersihan", "https://loremflickr.com/400/300/trash,broken"),
        ComplaintModel("Genangan Air Hujan", "Infrastruktur", "https://loremflickr.com/400/300/flood,puddle"),
        ComplaintModel("Pos Keamanan Vandal", "Ketertiban", "https://loremflickr.com/400/300/guard,security"),
        ComplaintModel("Taman Desa Tidak Terawat", "Lingkungan", "https://loremflickr.com/400/300/park,overgrown"),
        ComplaintModel("Fasilitas Balai Desa Rusak", "Fasilitas Umum", "https://loremflickr.com/400/300/building,damaged"),
        ComplaintModel("Saluran Irigasi Bocor", "Air & Sanitasi", "https://loremflickr.com/400/300/irrigation,canal"),
        ComplaintModel("MCK Umum Kotor", "Air & Sanitasi", "https://loremflickr.com/400/300/toilet,sanitation"),
        ComplaintModel("Kabel Listrik Menjuntai", "Fasilitas Umum", "https://loremflickr.com/400/300/electric,cable"),
        ComplaintModel("Papan Nama Jalan Hilang", "Infrastruktur", "https://loremflickr.com/400/300/road,sign"),
        ComplaintModel("Pedagang Kaki Lima Liar", "Ketertiban", "https://loremflickr.com/400/300/street,vendor,market"),
        ComplaintModel("Kebisingan Malam Hari", "Ketertiban", "https://loremflickr.com/400/300/night,crowd,noise"),
        ComplaintModel("Limbah Industri Rumahan", "Lingkungan", "https://loremflickr.com/400/300/pollution,factory,smoke"),
        ComplaintModel("Kebakaran Lahan Kosong", "Lingkungan", "https://loremflickr.com/400/300/fire,burning,field"),
        ComplaintModel("Aspal Mengelupas Rt.12", "Infrastruktur", "https://loremflickr.com/400/300/road,asphalt,crack"),
        ComplaintModel("Sumur Warga Kering", "Air & Sanitasi", "https://loremflickr.com/400/300/well,drought,dry"),
        ComplaintModel("Pagar Posyandu Roboh", "Fasilitas Umum", "https://loremflickr.com/400/300/fence,broken,collapsed"),
        ComplaintModel("Banjir Rob Wilayah Timur", "Infrastruktur", "https://loremflickr.com/400/300/flood,village,water"),
        ComplaintModel("Hewan Liar Masuk Kampung", "Lingkungan", "https://loremflickr.com/400/300/wild,animal,stray"),
        ComplaintModel("Trotoar Rusak Jl. Melati", "Infrastruktur", "https://loremflickr.com/400/300/sidewalk,broken,crack"),
        ComplaintModel("Pembuangan Sampah Ilegal", "Kebersihan", "https://loremflickr.com/400/300/garbage,illegal,dump"),
        ComplaintModel("Wifi Publik Tidak Aktif", "Fasilitas Umum", "https://loremflickr.com/400/300/wifi,router,technology"),
        ComplaintModel("Peternakan Tidak Berizin", "Ketertiban", "https://loremflickr.com/400/300/farm,livestock,cattle"),
        ComplaintModel("Gorong-gorong Tersumbat", "Infrastruktur", "https://loremflickr.com/400/300/sewer,drain,pipe"),
        ComplaintModel("Lapangan Olahraga Rusak", "Fasilitas Umum", "https://loremflickr.com/400/300/sports,field,abandoned"),
        ComplaintModel("Polusi Asap Pembakaran", "Lingkungan", "https://loremflickr.com/400/300/smoke,air,pollution"),
        ComplaintModel("Atap SD Desa Bocor", "Fasilitas Umum", "https://loremflickr.com/400/300/roof,rain,leak"),
        ComplaintModel("Kios Desa Tidak Terawat", "Fasilitas Umum", "https://loremflickr.com/400/300/shop,market,old"),
        ComplaintModel("Vandalisme Tembok Desa", "Ketertiban", "https://loremflickr.com/400/300/graffiti,wall,vandalism"),
        ComplaintModel("Jalan Licin Tanpa Rambu", "Infrastruktur", "https://loremflickr.com/400/300/road,wet,slippery"),
        ComplaintModel("Bangkai Hewan di Sungai", "Lingkungan", "https://loremflickr.com/400/300/river,dirty,pollution"),
        ComplaintModel("Tangki Septik Penuh", "Air & Sanitasi", "https://loremflickr.com/400/300/sewage,tank,water"),
        ComplaintModel("Pompa Air Desa Rusak", "Air & Sanitasi", "https://loremflickr.com/400/300/water,pump,broken"),
        ComplaintModel("Jembatan Kayu Patah", "Infrastruktur", "https://loremflickr.com/400/300/wooden,bridge,broken"),
        ComplaintModel("Penggalian Liar Tanah Desa", "Ketertiban", "https://loremflickr.com/400/300/excavation,construction,dig"),
        ComplaintModel("Puskesmas Kekurangan Obat", "Kesehatan", "https://loremflickr.com/400/300/clinic,medicine,health"),
        ComplaintModel("Ventilasi Gedung PKK Buruk", "Fasilitas Umum", "https://loremflickr.com/400/300/building,ventilation,air"),
        ComplaintModel("Jalur Evakuasi Banjir", "Infrastruktur", "https://loremflickr.com/400/300/flood,evacuation,emergency"),
        ComplaintModel("Pemasangan Listrik Ilegal", "Fasilitas Umum", "https://loremflickr.com/400/300/electricity,wire,illegal"),
        ComplaintModel("Kurangnya Tempat Bermain", "Fasilitas Umum", "https://loremflickr.com/400/300/playground,children,park"),
        ComplaintModel("Sungai Tercemar Limbah", "Lingkungan", "https://loremflickr.com/400/300/river,pollution,waste"),
        ComplaintModel("Penambangan Pasir Liar", "Ketertiban", "https://loremflickr.com/400/300/sand,mining,excavation"),
        ComplaintModel("Kondisi Pasar Desa Kumuh", "Kebersihan", "https://loremflickr.com/400/300/market,crowded,dirty"),
        ComplaintModel("Masjid Desa Perlu Renovasi", "Fasilitas Umum", "https://loremflickr.com/400/300/mosque,building,renovation")
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