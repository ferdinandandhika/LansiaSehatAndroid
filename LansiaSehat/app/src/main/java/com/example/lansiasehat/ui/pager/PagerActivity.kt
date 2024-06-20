package com.example.lansiasehat.ui.pager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.lansiasehat.ui.MainActivity
import com.example.lansiasehat.ui.tutorial.TutorialActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.example.lansiasehat.databinding.ActivityPagerBinding

class PagerActivity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager2
    private lateinit var btnCreateAccount: Button

    private lateinit var binding: ActivityPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        btnCreateAccount = binding.btnCreateAccount
        btnCreateAccount.setOnClickListener {
            val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
            val isFirstRun = sharedPreferences.getBoolean("isFirstRun", true)
            if (isFirstRun) {
                val intent = Intent(this, TutorialActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        mViewPager = binding.viewPager
        mViewPager.adapter = ViewPagerAdapter(this, this)
        TabLayoutMediator(binding.pageIndicator, mViewPager) { tab, position -> }.attach()
        mViewPager.offscreenPageLimit = 1
    }
}
