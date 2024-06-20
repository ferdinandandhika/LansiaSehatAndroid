package com.example.lansiasehat.ui.bmi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lansiasehat.R
import com.example.lansiasehat.databinding.FragmentBmiBinding
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal
import java.math.RoundingMode

class BmiFragment : Fragment() {

    private var _binding: FragmentBmiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBmiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calculateBtn.setOnClickListener {
            val weightText = binding.weightEDTX.text.toString()
            val heightText = binding.heightEDTX.text.toString()
            if (weightText.isNotEmpty() && heightText.isNotEmpty()) {
                val weight = weightText.toDouble()
                val height = heightText.toDouble() / 100
                if (weight > 0 && weight < 600 && height >= 0.50 && height < 2.50) {
                    val intent = Intent(requireContext(), BmiResultActivity::class.java)
                    intent.putExtra("bmi", calculateBMI(weight, height))
                    startActivity(intent)
                } else {
                    showErrorSnack("Isinya tidak benar")
                }
            } else {
                showErrorSnack("Isinya tidak lengkap")
            }
        }
    }

    private fun showErrorSnack(errorMsg: String) {
        val snackbar = Snackbar.make(binding.container, "error : $errorMsg !", Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundResource(R.color.colorRed)
        snackbar.show()
    }

    private fun calculateBMI(weight: Double, height: Double): Double = BigDecimal(weight / (height * height))
        .setScale(2, RoundingMode.HALF_EVEN).toDouble()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
