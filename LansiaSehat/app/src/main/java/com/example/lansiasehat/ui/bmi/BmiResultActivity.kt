package com.example.lansiasehat.ui.bmi

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lansiasehat.R
import com.example.lansiasehat.databinding.ActivityBmiResultBinding

class BmiResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBmiResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        val bmi = intent.getDoubleExtra("bmi", -1.0)
        if (bmi == -1.0) {
            binding.containerL.visibility = View.GONE
        } else {
            binding.bmiValueTV.text = bmi.toString()
            when {
                bmi < 18.5 -> {
                    binding.containerL.setBackgroundResource(R.color.colorYellow)
                    binding.bmiFlagImgView.setImageResource(R.drawable.exclamationmark)
                    binding.bmiLabelTV.text = "Berat Badan Kamu Kurang!"
                    binding.commentTV.text = "Berikut adalah beberapa saran untuk membantu Anda menambah berat badan"
                    binding.advice1IMG.setImageResource(R.drawable.nowater)
                    binding.advice1TV.text = "Jangan minum air sebelum makan"
                    binding.advice2IMG.setImageResource(R.drawable.bigmeal)
                    binding.advice2TV.text = "Gunakan piring yang lebih besar"
                    binding.advice3TV.text = "Tidur yang berkualitas"
                }
                bmi > 25 -> {
                    binding.containerL.setBackgroundResource(R.color.colorRed)
                    binding.bmiFlagImgView.setImageResource(R.drawable.warning)
                    binding.bmiLabelTV.text = "Berat Badan Kamu Berlebih!"
                    binding.commentTV.text = "Berikut adalah beberapa saran untuk membantu Anda menurunkan berat badan"
                    binding.advice1IMG.setImageResource(R.drawable.water)
                    binding.advice1TV.text = "Minum air setengah jam sebelum makan"
                    binding.advice2IMG.setImageResource(R.drawable.twoeggs)
                    binding.advice2TV.text = "Makan hanya dua kali sehari dan pastikan mengandung protein tinggi"
                    binding.advice3IMG.setImageResource(R.drawable.nosugar)
                    binding.advice3TV.text = "Hindari minum kopi atau teh dan makanan manis"
                }
                else -> {
                }
            }
        }
    }
}
