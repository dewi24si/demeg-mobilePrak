package com.example.demeg_flower.info

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.demeg_flower.databinding.ItemInfoBinding

class InfoAdapter(
    context: Context,
    private val infoList: List<InfoModel>
) : ArrayAdapter<InfoModel>(context, 0, infoList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemInfoBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root

        val data = infoList[position]

        Glide.with(context)
            .load(data.avatarUrl)
            .circleCrop()
            .into(binding.avatarImg)

        binding.textNama.text = data.namaWarga
        binding.textKeterangan.text = data.keterangan

        view.setOnClickListener {
            com.google.android.material.snackbar.Snackbar.make(
                parent,
                "${data.namaWarga} – ${data.keterangan}",
                com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}
