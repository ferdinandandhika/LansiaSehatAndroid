package com.example.lansiasehat.ui.tutorial

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lansiasehat.R

class TutorialViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val context: Context
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TutorialFragment.newInstance(R.drawable.tutor0)
            1 -> TutorialFragment.newInstance(R.drawable.info1)
            2 -> TutorialFragment.newInstance(R.drawable.info2)
            3 -> TutorialFragment.newInstance(R.drawable.info3)
            4 -> TutorialFragment.newInstance(R.drawable.info4)
            5 -> TutorialFragment.newInstance(R.drawable.tutor1)
            6 -> TutorialFragment.newInstance(R.drawable.tutor2)
            7 -> TutorialFragment.newInstance(R.drawable.tutor3)
            8 -> TutorialFragment.newInstance(R.drawable.tutor4)
            9 -> TutorialFragment.newInstance(R.drawable.tutor5)
            10 -> TutorialFragment.newInstance(R.drawable.tutor6)
            11 -> TutorialFragment.newInstance(R.drawable.tutor7)
            12 -> TutorialFragment.newInstance(R.drawable.tutor8)
            13 -> TutorialFragment.newInstance(R.drawable.tutor9)
            14 -> TutorialFragment.newInstance(R.drawable.tutor10)
            15 -> TutorialFragment.newInstance(R.drawable.tutor11)
            17 -> TutorialFragment.newInstance(R.drawable.tutor12)
            else -> TutorialFragment.newInstance(R.drawable.tutor13)
        }
    }

    override fun getItemCount(): Int {
        return 19
    }
}