package com.example.lansiasehat.ui.nutrisi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lansiasehat.R

class KonsumsiAdapter(
    private val context: Context,
    private val gambarKonsumsi: IntArray,
    private val judulKonsumsi: Array<String>
) : RecyclerView.Adapter<KonsumsiAdapter.KonsumsiViewHolder>(), Filterable {

    private var filteredGambarKonsumsi: IntArray = gambarKonsumsi
    private var filteredJudulKonsumsi: Array<String> = judulKonsumsi

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KonsumsiViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nutrisi, parent, false)
        return KonsumsiViewHolder(view)
    }

    override fun onBindViewHolder(holder: KonsumsiViewHolder, position: Int) {
        holder.imageView.setImageResource(filteredGambarKonsumsi[position])
        holder.textView.text = filteredJudulKonsumsi[position]

        holder.itemView.setOnClickListener {
            val originalPosition = gambarKonsumsi.indexOf(filteredGambarKonsumsi[position])
            val intent = Intent(context, DetailKonsumsiActivity::class.java).apply {
                putExtra("KONSUMSI_ID", originalPosition)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = filteredGambarKonsumsi.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase() ?: ""
                val filterResults = FilterResults()

                if (query.isEmpty()) {
                    filterResults.values = Pair(gambarKonsumsi, judulKonsumsi)
                } else {
                    val filteredList = judulKonsumsi.mapIndexedNotNull { index, title ->
                        if (title.lowercase().contains(query)) {
                            Pair(gambarKonsumsi[index], title)
                        } else {
                            null
                        }
                    }
                    filterResults.values = Pair(
                        filteredList.map { it.first }.toIntArray(),
                        filteredList.map { it.second }.toTypedArray()
                    )
                }

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                val (filteredGambar, filteredJudul) = results?.values as Pair<IntArray, Array<String>>
                filteredGambarKonsumsi = filteredGambar
                filteredJudulKonsumsi = filteredJudul
                notifyDataSetChanged()
            }
        }
    }

    class KonsumsiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.detail_image_view)
        val textView: TextView = itemView.findViewById(R.id.detail_text_view)
    }
}
