package com.example.lansiasehat.ui.nutrisi

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lansiasehat.R

class KonsumsiActivity : AppCompatActivity() {
    private lateinit var adapter: KonsumsiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konsumsi)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_nutrisi)

        val typedArray = resources.obtainTypedArray(R.array.gambarkonsumsi_array)
        val gambarKonsumsi = IntArray(typedArray.length()) { i ->
            typedArray.getResourceId(i, -1)
        }
        typedArray.recycle()

        val judulKonsumsi = resources.getStringArray(R.array.judulkonsumsi_array)

        adapter = KonsumsiAdapter(this, gambarKonsumsi, judulKonsumsi)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.setIconifiedByDefault(false) 


        searchView.setOnClickListener {
            searchView.isIconified = false
            searchView.isFocusable = true
            searchView.isFocusableInTouchMode = true
            searchView.requestFocus()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }
}