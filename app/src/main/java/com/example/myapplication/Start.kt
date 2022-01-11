package com.example.myapplication

import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentStartBinding
import com.google.android.material.tabs.TabLayoutMediator


class Start : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private var fabVisibility = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //main fab
        val floatingActionButton = binding.floatingActionButton

        floatingActionButton.setOnClickListener {

            //set secondary fab visibility
            if (fabVisibility == true) {

                //change main fab icon
                floatingActionButton.setImageIcon(
                    Icon.createWithResource(
                        activity,
                        R.drawable.ic_baseline_add_24
                    )
                )

                //change fab visibility
                binding.fab1.isVisible = false
                binding.fab2.isVisible = false
                fabVisibility = false
            }

            //set secondary fab visibility
            else {

                //change main fab icon
                floatingActionButton.setImageIcon(
                    Icon.createWithResource(
                        activity,
                        R.drawable.ic_baseline_clear_24
                    )
                )

                //change fab visibility
                binding.fab1.isVisible = true
                binding.fab2.isVisible = true
                fabVisibility = true
            }
        }
        //go to browser
        binding.fab1.setOnClickListener {
            findNavController().navigate(R.id.action_to_browser)
        }

        //download by link
        binding.fab2.setOnClickListener {
            findNavController().navigate(R.id.action_to_Link)
        }

        //viewPager2
        val viewpager = binding.viewPager
        viewpager.adapter = ViewPagerAdapter(this)

        //tabLatout
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, binding.viewPager) {tab, position ->
            //create tabs names
            val tabs = Tabs().tabs
            //set tabs text
            tab.text = tabs[position]
        }.attach()
    }
}