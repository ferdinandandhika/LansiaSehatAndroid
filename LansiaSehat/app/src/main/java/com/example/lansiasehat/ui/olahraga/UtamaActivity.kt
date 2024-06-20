package com.example.lansiasehat.ui.olahraga

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lansiasehat.R

class UtamaActivity : AppCompatActivity(), OlahragaAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utama)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_hari)

        val typedArray = resources.obtainTypedArray(R.array.listset_array)
        val gambarPendinginan = IntArray(typedArray.length()) { i ->
            typedArray.getResourceId(i, -1)
        }
        typedArray.recycle()

        val adapter = OlahragaAdapter(this, gambarPendinginan, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onItemClick(position: Int) {
        val intent = when (position) {
            0 -> Intent(this, Set1Activity::class.java)
            1 -> Intent(this, Set2Activity::class.java)
            2 -> Intent(this, Set3Activity::class.java)
            else -> return
        }
        startActivity(intent)
    }
}
