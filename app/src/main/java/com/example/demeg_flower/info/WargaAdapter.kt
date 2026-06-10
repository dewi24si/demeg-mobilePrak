package com.example.demeg_flower.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demeg_flower.data.entity.WargaEntity
import com.example.demeg_flower.databinding.ItemInfoBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class WargaAdapter(
    private val wargaList: List<WargaEntity>,
    private val infoFragment: InfoFragment
) : RecyclerView.Adapter<WargaAdapter.WargaViewHolder>() {

    inner class WargaViewHolder(val binding: ItemInfoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WargaViewHolder {
        val binding = ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WargaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WargaViewHolder, position: Int) {
        val warga = wargaList[position]

        holder.binding.textNama.text = warga.namaWarga
        holder.binding.textKeterangan.text = warga.jabatan

        Glide.with(holder.itemView.context)
            .load(warga.avatarUrl)
            .circleCrop()
            .into(holder.binding.avatarImg)

        holder.binding.btnDeleteWarga.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Warga")
                .setMessage("Hapus ${warga.namaWarga} dari daftar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    infoFragment.deleteWarga(warga)
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun getItemCount(): Int = wargaList.size
}
