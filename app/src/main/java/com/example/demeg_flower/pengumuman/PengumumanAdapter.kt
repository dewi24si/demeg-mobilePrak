package com.example.demeg_flower.pengumuman

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demeg_flower.data.entity.PengumumanEntity
import com.example.demeg_flower.databinding.ItemPengumumanBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PengumumanAdapter(
    private val list: List<PengumumanEntity>,
    private val fragment: PengumumanFragment
) : RecyclerView.Adapter<PengumumanAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPengumumanBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPengumumanBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvJudul.text = item.judul
        holder.binding.tvIsi.text = item.isi

        val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("id", "ID"))
        holder.binding.tvTanggal.text = sdf.format(Date(item.createdAt))

        holder.binding.btnDeletePengumuman.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Pengumuman")
                .setMessage("Hapus pengumuman \"${item.judul}\"?")
                .setPositiveButton("Ya") { dialog, _ ->
                    fragment.deletePengumuman(item)
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun getItemCount(): Int = list.size
}
