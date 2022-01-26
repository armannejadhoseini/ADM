package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.usecase.MonitorProgressImpl
import com.example.myapplication.adapters.DownloadQueueRecyclerView
import com.example.myapplication.adapters.FinishedRecyclerView
import com.example.myapplication.databinding.DownloadQueueBinding
import com.example.myapplication.viewModels.DownloadQueueViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DownloadQueue : Fragment() {

    private var _binding: DownloadQueueBinding? = null
    private val binding get() = _binding!!
    val viewModel: DownloadQueueViewModel by viewModels()
    @Inject
    lateinit var monitorProgressImpl: MonitorProgressImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DownloadQueueBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //recyclerView
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        //get the value of flow and pass to adapter
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.listOfDownloads.collect {
                recyclerView.adapter = DownloadQueueRecyclerView(it, monitorProgressImpl)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}