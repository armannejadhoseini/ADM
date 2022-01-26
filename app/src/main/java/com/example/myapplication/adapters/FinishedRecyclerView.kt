package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entity.DownloadEntity
import com.example.myapplication.databinding.DownloadItemBinding
import kotlinx.coroutines.flow.Flow

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
        var text = downloadEntity[position].fileName
        if (text.length > 20) {
            text = text.substring(0, 20)
        }
        holder.fileName.text = text
    }

    override fun getItemCount(): Int {
        return downloadEntity.size
    }

}