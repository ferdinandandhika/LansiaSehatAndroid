package com.example.lansiasehat.ui.tutorial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.lansiasehat.databinding.ActivityTutorialBinding
import com.example.lansiasehat.ui.MainActivity
import com.google.android.material.tabs.TabLayoutMediator

class TutorialActivity : AppCompatActivity() {
    private lateinit var mViewPager: ViewPager2
    private lateinit var textEnd: TextView
    private lateinit var btnNextStep: ImageButton

    private lateinit var binding: ActivityTutorialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mViewPager = binding.viewPager
        textEnd = binding.textEnd
        btnNextStep = binding.btnNextStep
        mViewPager.adapter = TutorialViewPagerAdapter(this, this)
        mViewPager.offscreenPageLimit = 1
        TabLayoutMediator(binding.pageIndicator, mViewPager) { _, _ -> }.attach()
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 12) {
                    btnNextStep.visibility = View.GONE
                    textEnd.visibility = View.VISIBLE
                } else {
                    btnNextStep.visibility = View.VISIBLE
                    textEnd.visibility = View.GONE
                }
            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(arg0: Int) {}
        })

        textEnd.setOnClickListener {
            val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putBoolean("isFirstRun", false)
                apply()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnNextStep.setOnClickListener {
            if (mViewPager.currentItem == mViewPager.adapter?.itemCount?.minus(1)) {
                // If it's the last item, go to MainActivity
                val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putBoolean("isFirstRun", false)
                    apply()
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Otherwise, go to the next item
                mViewPager.setCurrentItem(mViewPager.currentItem + 1, true)
            }
        }
    }

}