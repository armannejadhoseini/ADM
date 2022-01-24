package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entity.DownloadEntity
import com.example.myapplication.databinding.DownloadItemBinding

class FinishedRecyclerView(
    val downloadEntity: List<DownloadEntity>
) : RecyclerView.Adapter<FinishedRecyclerView.viewHolder>() {
    private lateinit var binding: DownloadItemBinding

    inner class viewHolder(binding: DownloadItemBinding) : RecyclerView.ViewHolder(binding.root) {
            val fileName: TextView

            init {
                fileName = binding.downloadName
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        binding = DownloadItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.fileName.text = downloadEntity[position].fileName
    }

    override fun getItemCount(): Int {
        return downloadEntity.size
    }

}