package com.example.myapplication

import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var fabVisibility = false
    private var browser = Browser()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //navigationview toggle
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, 0, 0)
        toggle.syncState()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController


//        //main fab
//        val floatingActionButton = binding.floatingActionButton
//
//        floatingActionButton.setOnClickListener {
//
//            //set secondary fab visibility
//            if (fabVisibility == true) {
//
//                //change main fab icon
//                floatingActionButton.setImageIcon(
//                    Icon.createWithResource(
//                        this,
//                        R.drawable.ic_baseline_add_24
//                    )
//                )
//
//                //change fab visibility
//                binding.fab1.isVisible = false
//                binding.fab2.isVisible = false
//                fabVisibility = false
//            }
//
//            //set secondary fab visibility
//            else {
//
//                //change main fab icon
//                floatingActionButton.setImageIcon(
//                    Icon.createWithResource(
//                        this,
//                        R.drawable.ic_baseline_clear_24
//                    )
//                )
//
//                //change fab visibility
//                binding.fab1.isVisible = true
//                binding.fab2.isVisible = true
//                fabVisibility = true
//            }
//        }
//        //go to browser
//        binding.fab1.setOnClickListener {
//            supportFragmentManager.beginTransaction().add(browser, "browser").addToBackStack("browser").commit()
//        }

//        //viewPager2
//        val viewpager = binding.viewPager
//        //viewpager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
//
//        //tabLatout
//        val tabLayout = binding.tabLayout
//        TabLayoutMediator(tabLayout, binding.viewPager) {tab, position ->
//            //create tabs names
//            val tabs = Tabs().tabs
//            //set tabs text
//            tab.text = tabs[position]
//        }.attach()


    }

}