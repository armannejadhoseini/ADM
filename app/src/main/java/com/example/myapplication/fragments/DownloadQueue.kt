package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.entity.DownloadProgress
import com.example.myapplication.adapters.DownloadQueueRecyclerView
import com.example.myapplication.databinding.DownloadQueueBinding
import com.example.myapplication.viewModels.DownloadQueueViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DownloadQueue : Fragment() {

    private var _binding: DownloadQueueBinding? = null
    private val binding get() = _binding!!
    val viewModel: DownloadQueueViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DownloadQueueBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listOfFiles.observe(viewLifecycleOwner, Observer {
            //recyclerView
            val recyclerView = binding.recyclerView
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = DownloadQueueRecyclerView(it)
            Log.d("TAG", "onViewCreated: $it")
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}