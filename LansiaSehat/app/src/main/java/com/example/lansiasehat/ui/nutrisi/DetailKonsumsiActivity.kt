package com.example.lansiasehat.ui.nutrisi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lansiasehat.R

class DetailKonsumsiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_konsumsi)

        val konsumsiId = intent.getIntExtra("KONSUMSI_ID", -1)
        val imageView = findViewById<ImageView>(R.id.gambarkonsumsi)
        val descriptionView = findViewById<TextView>(R.id.penjelasankonsumsi)
        val kaloriView = findViewById<TextView>(R.id.kalori)
        val proteinView = findViewById<TextView>(R.id.protein)
        val judulView = findViewById<TextView>(R.id.judul)
        val sumberView = findViewById<TextView>(R.id.sumber)

        if (konsumsiId != -1) {
            val imageArray = resources.obtainTypedArray(R.array.gambarkonsumsi_array)
            val descriptionArray = resources.getStringArray(R.array.deskripsikonsumsi_array)
            val kaloriArray = resources.getStringArray(R.array.kalorikonsumsi_array)
            val proteinArray = resources.getStringArray(R.array.proteinkonsumsi_array)
            val judulArray = resources.getStringArray(R.array.judulkonsumsi_array)

            val imageResId = imageArray.getResourceId(konsumsiId, -1)
            val description = descriptionArray[konsumsiId]
            val kalori = kaloriArray[konsumsiId]
            val protein = proteinArray[konsumsiId]
            val judul = judulArray[konsumsiId]

            imageArray.recycle()

            imageView.setImageResource(imageResId)
            descriptionView.text = description
            kaloriView.text = kalori
            proteinView.text = protein
            judulView.text = judul
        }

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
 
        sumberView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.myfitnesspal.com/nutrition-facts-calories"))
            startActivity(intent)
        }
    }
}
