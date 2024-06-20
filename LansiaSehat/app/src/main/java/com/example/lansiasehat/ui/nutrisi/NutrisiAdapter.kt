package com.example.lansiasehat.ui.nutrisi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.lansiasehat.R

class NutrisiAdapter(private val context: Context, private val nutrisiList: IntArray) :
    RecyclerView.Adapter<NutrisiAdapter.NutrisiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutrisiViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_olahraga, parent, false)
        return NutrisiViewHolder(view)
    }

    override fun onBindViewHolder(holder: NutrisiViewHolder, position: Int) {
        holder.imageNutrisi.setImageResource(nutrisiList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailNutrisiActivity::class.java).apply {
            putExtra("NUTRISI_ID", position)
        }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = nutrisiList.size

    class NutrisiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageNutrisi: ImageView = itemView.findViewById(R.id.imageHari)
    }
}
