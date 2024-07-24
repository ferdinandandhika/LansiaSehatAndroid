package com.example.lansiasehat.ui.nutrisi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lansiasehat.R

class NutrisiAdapter(
    private val context: Context,
    private val gambarNutrisi: IntArray,
    private val judulNutrisi: Array<String>
) : RecyclerView.Adapter<NutrisiAdapter.NutrisiViewHolder>() {

    private var filteredGambarNutrisi: IntArray = gambarNutrisi
    private var filteredJudulNutrisi: Array<String> = judulNutrisi

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutrisiViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nutrisi, parent, false)
        return NutrisiViewHolder(view)
    }

    override fun onBindViewHolder(holder: NutrisiViewHolder, position: Int) {
        holder.imageView.setImageResource(filteredGambarNutrisi[position])
        holder.textView.text = filteredJudulNutrisi[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailNutrisiActivity::class.java).apply {
                putExtra("NUTRISI_ID", position)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = filteredGambarNutrisi.size

    class NutrisiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.detail_image_view)
        val textView: TextView = itemView.findViewById(R.id.detail_text_view)
    }
}
