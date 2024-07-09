package com.example.lansiasehat.ui.nutrisi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lansiasehat.R

class DetailNutrisiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_nutrisi)

        val nutrisiId = intent.getIntExtra("NUTRISI_ID", -1)
        val btnHalodoc = findViewById<TextView>(R.id.halodoc)
        val imageView = findViewById<ImageView>(R.id.player_view)
        val textView = findViewById<TextView>(R.id.penjelasan)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_detail_nutrisi)

        if (nutrisiId != -1) {
            val imageArray = resources.obtainTypedArray(R.array.gambarnutrisi_array)
            val descriptionArray = resources.getStringArray(R.array.penjelasannutrisi_array)

            val imageResId = imageArray.getResourceId(nutrisiId, -1)
            val description = descriptionArray[nutrisiId]

            imageArray.recycle()

            imageView.setImageResource(imageResId)
            textView.text = description

            btnHalodoc.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.halodoc.com/artikel/ketahui-7-asupan-nutrisi-penting-untuk-lansia"))
                startActivity(intent)
            }

            val (detailList, konsumsiIdOffset) = when (nutrisiId) {
                0 -> getDetailList(R.array.gambarb12_array, R.array.namab12_array) to 0
                1 -> getDetailList(R.array.gambarcalcium_array, R.array.namacalcium_array) to 4
                2 -> getDetailList(R.array.gambarvitd_array, R.array.namavitd_array) to 6
                3 -> getDetailList(R.array.gambarmag_array, R.array.namamag_array) to 8
                4 -> getDetailList(R.array.gambarserat_array, R.array.namaserat_array) to 12
                5 -> getDetailList(R.array.gambaromega_array, R.array.namaomega_array) to 18
                6 -> getDetailList(R.array.gambarair_array, R.array.namaair_array) to 22
                else -> emptyList<Pair<Int, String>>() to 0
            }

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = DetailNutrisiAdapter(this, detailList, R.layout.item_nutrisi, konsumsiIdOffset)
        }

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun getDetailList(imageArrayResId: Int, nameArrayResId: Int): List<Pair<Int, String>> {
        val imageArray = resources.obtainTypedArray(imageArrayResId)
        val nameArray = resources.getStringArray(nameArrayResId)

        val detailList = List(imageArray.length()) { i ->
            Pair(imageArray.getResourceId(i, -1), nameArray[i])
        }

        imageArray.recycle()
        return detailList
    }
}