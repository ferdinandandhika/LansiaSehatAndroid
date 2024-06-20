package com.example.lansiasehat.ui.nutrisi

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lansiasehat.R
import com.example.lansiasehat.ui.olahraga.OlahragaAdapter

class NutrisiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrisi)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_nutrisi)

        val typedArray = resources.obtainTypedArray(R.array.gambarnutrisi_array)
        val gambarNutrisi = IntArray(typedArray.length()) { i ->
            typedArray.getResourceId(i, -1)
        }
        typedArray.recycle()

        val adapter = NutrisiAdapter(this, gambarNutrisi)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }
}