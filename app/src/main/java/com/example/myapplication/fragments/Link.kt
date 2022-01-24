package com.example.myapplication.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.LinkBinding
import com.example.myapplication.viewModels.LinkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class Link : Fragment() {

    private var _binding: LinkBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LinkViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LinkBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //download the file on btn click
        binding.btnDownload.setOnClickListener {

            //check if the input url is valid
            if (binding.editText.text.toString().isNotEmpty()) {

                //set url and files name
                viewModel.setUrl(binding.editText.text.toString())

                //set mime type of downloading file
                viewModel.setMimeType(binding.editText.text.toString())

                //get the files name and fill it automatically
                binding.editText2.setText(viewModel.setFileName())

                //display a staring message
                Toast.makeText(context, "Started Downloading", Toast.LENGTH_SHORT).show()

                //start downloading
                GlobalScope.launch(Dispatchers.IO) {
                    viewModel.download()
                }
            }
        }

    }
}