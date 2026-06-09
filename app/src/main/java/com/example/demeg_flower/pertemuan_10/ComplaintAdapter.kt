package com.example.demeg_flower.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demeg_flower.databinding.ItemComplaintBinding

class ComplaintAdapter(
    private val complaintList: List<ComplaintModel>,
    private val onItemClick: (ComplaintModel) -> Unit
) : RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder>() {

    inner class ComplaintViewHolder(val binding: ItemComplaintBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintViewHolder {
        val binding = ItemComplaintBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ComplaintViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComplaintViewHolder, position: Int) {
        val item = complaintList[position]
        with(holder.binding) {
            tvComplaintTitle.text = item.title
            tvComplaintCategory.text = item.category
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .into(imgComplaint)
            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = complaintList.size
}
