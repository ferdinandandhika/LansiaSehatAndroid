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

class DetailNutrisiAdapter(
    private val context: Context,
    private val detailList: List<Pair<Int, String>>,
    private val itemLayoutResId: Int,
    private val konsumsiIdOffset: Int // Added offset to send the correct position
) : RecyclerView.Adapter<DetailNutrisiAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(context).inflate(itemLayoutResId, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val (imageResId, name) = detailList[position]
        holder.detailImageView.setImageResource(imageResId)
        holder.detailTextView.text = name

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailKonsumsiActivity::class.java).apply {
                putExtra("KONSUMSI_ID", position + konsumsiIdOffset)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = detailList.size

    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val detailImageView: ImageView = itemView.findViewById(R.id.detail_image_view)
        val detailTextView: TextView = itemView.findViewById(R.id.detail_text_view)
    }
}