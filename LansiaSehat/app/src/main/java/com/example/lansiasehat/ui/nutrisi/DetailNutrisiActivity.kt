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
        var btnHalodoc = findViewById<TextView>(R.id.halodoc)
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

            val detailList = when (nutrisiId) {
                0 -> getDetailList(R.array.gambarb12_array, R.array.namab12_array)
                1 -> getDetailList(R.array.gambarcalcium_array, R.array.namacalcium_array)
                2 -> getDetailList(R.array.gambarvitd_array, R.array.namavitd_array)
                3 -> getDetailList(R.array.gambarmag_array, R.array.namamag_array)
                4 -> getDetailList(R.array.gambarserat_array, R.array.namaserat_array)
                5 -> getDetailList(R.array.gambaromega_array, R.array.namaomega_array)
                6 -> getDetailList(R.array.gambarair_array, R.array.namaair_array)
                else -> emptyList()
            }

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = DetailNutrisiAdapter(this, detailList, R.layout.item_nutrisi)
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