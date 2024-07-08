package com.example.lansiasehat.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lansiasehat.R
import com.example.lansiasehat.databinding.FragmentHomeBinding
import com.example.lansiasehat.ui.kuis.KuisActivity
import com.example.lansiasehat.ui.nutrisi.NutrisiActivity
import com.example.lansiasehat.ui.olahraga.OlahragaActivity
import com.example.lansiasehat.ui.tutorial.TutorialActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val olahragaCard: CardView = binding.olahraga
        olahragaCard.setOnClickListener {
            val intent = Intent(requireContext(), OlahragaActivity::class.java)
            startActivity(intent)
        }

        val nutrisiCard: CardView = binding.nutrisi
        nutrisiCard.setOnClickListener {
            val intent = Intent(requireContext(), NutrisiActivity::class.java)
            startActivity(intent)
        }

        val tutorialCard: CardView = binding.tutorial
        tutorialCard.setOnClickListener {
            val intent = Intent(requireContext(), TutorialActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}