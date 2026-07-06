package com.example.demeg_flower.pertemuan_9

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.demeg_flower.databinding.ItemKontakBinding

/**
 * Pertemuan 9 – CustomAdapter untuk ListView
 * Menampilkan gambar (via Glide), nama, dan jabatan per item.
 */
class KontakAdapter(
    context: Context,
    private val kontakList: List<KontakModel>
) : ArrayAdapter<KontakModel>(context, 0, kontakList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemKontakBinding = ItemKontakBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        val data = kontakList[position]

        Glide.with(context)
            .load(data.avatarUrl)
            .circleCrop()
            .into(binding.avatarImg)

        binding.textName.text = data.nama
        binding.textRole.text = data.jabatan

        return binding.root
    }
}
