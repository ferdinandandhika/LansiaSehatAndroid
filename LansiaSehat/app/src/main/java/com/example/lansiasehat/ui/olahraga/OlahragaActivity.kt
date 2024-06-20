package com.example.lansiasehat.ui.olahraga

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lansiasehat.R

class OlahragaActivity : AppCompatActivity(), OlahragaAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olahraga)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_hari)

        val typedArray = resources.obtainTypedArray(R.array.gambarolahraga_array)
        val gambarBagian = IntArray(typedArray.length()) { i ->
            typedArray.getResourceId(i, -1)
        }
        typedArray.recycle()


        val adapter = OlahragaAdapter(this, gambarBagian, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                val intent = Intent(this, PemanasanActivity::class.java)
                startActivity(intent)
            }
            1 -> {
                val intent = Intent(this, UtamaActivity::class.java)
                startActivity(intent)
            }
            2 -> {
                val intent = Intent(this, PendinginanActivity::class.java)
                startActivity(intent)
            }
        }
    }

}
