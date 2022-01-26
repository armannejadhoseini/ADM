package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapters.FinishedRecyclerView
import com.example.myapplication.databinding.FinishedBinding
import com.example.myapplication.viewModels.FinishedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Finished : Fragment() {

    private var _binding: FinishedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FinishedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set adaptor for recyclerview
        val recyclerView = binding.finishedRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        //get the value of flow and pass to adapter
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.log.collect {
                recyclerView.adapter = FinishedRecyclerView(it)
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}