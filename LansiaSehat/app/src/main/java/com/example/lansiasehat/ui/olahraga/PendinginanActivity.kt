package com.example.lansiasehat.ui.olahraga

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lansiasehat.R
import com.example.lansiasehat.utils.NetworkUtils
import com.google.firebase.storage.FirebaseStorage
import java.util.concurrent.CountDownLatch

class PendinginanActivity : AppCompatActivity(), OlahragaAdapter.OnItemClickListener {
    private val videoPaths = arrayOf(
        "pendinginan_images/akhir1.mp4",
        "pendinginan_images/akhir2.mp4",
        "pendinginan_images/akhir3.mp4",
        "pendinginan_images/akhir4.mp4",
        "pendinginan_images/akhir5.mp4",
        "pendinginan_images/akhir6.mp4"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pendinginan)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_hari)

        val typedArray = resources.obtainTypedArray(R.array.listpendinginan_array)
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
        if (!NetworkUtils.isNetworkAvailable(this)) {
            Toast.makeText(this, "Tidak ada koneksi internet. Silakan nyalakan koneksi internet Anda.", Toast.LENGTH_LONG).show()
            return
        }

        if (position in videoPaths.indices) {
            val storageReference = FirebaseStorage.getInstance().reference
            val videoUrls = Array(videoPaths.size) { "" }
            val latch = CountDownLatch(videoPaths.size)

            videoPaths.forEachIndexed { index, path ->
                val videoRef = storageReference.child(path)
                videoRef.downloadUrl.addOnSuccessListener { uri ->
                    videoUrls[index] = uri.toString()
                    latch.countDown()
                }.addOnFailureListener { exception ->
                    Log.e("PendinginanActivity", "Error getting video URL", exception)
                    latch.countDown()
                }
            }

            Thread {
                latch.await()
                runOnUiThread {
                    val intent = Intent(this, DetailActivity::class.java).apply {
                        putExtra("video_urls", videoUrls)
                        putExtra("POSITION", position)
                        putExtra("SOURCE", "PendinginanActivity")
                    }
                    startActivity(intent)
                }
            }.start()
        } else {
            Log.e("PendinginanActivity", "Invalid position: $position")
        }
    }
}