package com.example.myapplication.adapters

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.example.data.entity.DownloadEntity
import com.example.domain.usecase.MonitorProgressImpl
import com.example.myapplication.databinding.DownloadItemBinding
import dagger.hilt.EntryPoint
import javax.inject.Inject


class DownloadQueueRecyclerView @Inject constructor(
    val downloadEntity: List<DownloadEntity>,
    val monitorProgressImpl: MonitorProgressImpl
) : RecyclerView.Adapter<DownloadQueueRecyclerView.viewHolder>() {

    private lateinit var binding: DownloadItemBinding

    inner class viewHolder(binding: DownloadItemBinding) : RecyclerView.ViewHolder(binding.root) {
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
        holder.text.text = downloadEntity[position].fileName
        holder.progress.progress = monitorProgressImpl.monitor(downloadEntity[position].downloadId)
    }

    override fun getItemCount(): Int {
        return downloadEntity.size
    }

    @SuppressLint("Range")
    fun monitor(downloadManager: DownloadManager): Int {
        var isDownloading = false
        //check download status
        val cursor = downloadManager.query(DownloadManager.Query())
        if (cursor.moveToFirst()) {
            if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_RUNNING)
                isDownloading = true
        }

        //do while download is running
        while (isDownloading) {
            val cursor = downloadManager.query(
                DownloadManager.Query().setFilterByStatus(DownloadManager.STATUS_RUNNING)
            )
            if (cursor.moveToFirst()) {
                //check if download is running or not
                when (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                    //if its running
                    (DownloadManager.STATUS_RUNNING) -> {
                        val totalBytes =
                            cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                        if (totalBytes > 0) {
                            val downloadedBytes =
                                cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                            val progress = (downloadedBytes * 100 / totalBytes).toInt()
                            return progress
                        }
                    }
                    //if finished
                    (DownloadManager.STATUS_SUCCESSFUL) -> {
                        isDownloading = false
                    }
                }
            }
            cursor.close()
        }
        return 0
    }


}