package com.example.lansiasehat.ui.rencana

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lansiasehat.R
import com.example.lansiasehat.model.Todo

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val todo = intent.getParcelableExtra<Todo>("todo")

        val backButton: ImageButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        if (todo != null) {
            findViewById<TextView>(R.id.detailTitle).text = todo.title
            findViewById<TextView>(R.id.detailDescription).text = todo.description
            findViewById<TextView>(R.id.detailDate).text = todo.date
            findViewById<TextView>(R.id.detailTime).text = todo.time
            findViewById<TextView>(R.id.detailCategory).text = todo.category

            val images = resources.obtainTypedArray(R.array.gambarcategory_array)
            val categoryArray = resources.getStringArray(R.array.category_array)
            val categoryIndex = categoryArray.indexOf(todo.category)
            if (categoryIndex >= 0) {
                findViewById<ImageView>(R.id.detailImage).setImageResource(images.getResourceId(categoryIndex, -1))
            }
            images.recycle()
        }
    }
}