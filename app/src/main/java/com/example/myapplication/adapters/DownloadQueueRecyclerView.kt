package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.example.data.entity.DownloadProgress
import com.example.myapplication.databinding.DownloadItemBinding

class DownloadQueueRecyclerView(val downloadProgress: List<DownloadProgress>): RecyclerView.Adapter<DownloadQueueRecyclerView.viewHolder>() {
    private lateinit var binding: DownloadItemBinding

    inner class viewHolder(binding: DownloadItemBinding): RecyclerView.ViewHolder(binding.root) {
        val text: TextView
        val progress: ProgressBar

        init {
            text = binding.downloadName
            progress = binding.progressBar
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        binding = DownloadItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.text.text = downloadProgress[position].fileName
        holder.progress.progress = downloadProgress[position].progress
    }

    override fun getItemCount(): Int {
        return downloadProgress.size
    }


}