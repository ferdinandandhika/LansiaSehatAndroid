package com.example.lansiasehat.ui.olahraga

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lansiasehat.R
import com.example.lansiasehat.utils.NetworkUtils
import com.google.android.exoplayer2.util.Log
import com.google.firebase.storage.FirebaseStorage
import java.util.concurrent.CountDownLatch

class Set2Activity : AppCompatActivity(), OlahragaAdapter.OnItemClickListener {
    private val videoPaths = arrayOf(
        "set2/set2_1.mp4",
        "set2/set2_2.mp4",
        "set2/set2_3.mp4",
        "set2/set2_4.mp4",
        "set2/set2_5.mp4",
        "set2/set2_6.mp4"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set2)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        val typedArray = resources.obtainTypedArray(R.array.gambarset2_array)
        val gambarSet2 = IntArray(typedArray.length()) { i ->
            typedArray.getResourceId(i, -1)
        }
        typedArray.recycle()

        val adapter = OlahragaAdapter(this, gambarSet2, this)
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
                    Log.e("Set2Activity", "Error getting video URL", exception)
                    latch.countDown()
                }
            }

            Thread {
                latch.await()
                runOnUiThread {
                    val intent = Intent(this, DetailActivity::class.java).apply {
                        putExtra("video_urls", videoUrls)
                        putExtra("POSITION", position)
                        putExtra("SOURCE", "Set2Activity")
                    }
                    startActivity(intent)
                }
            }.start()
        } else {
            Log.e("Set2Activity", "Invalid position: $position")
        }
    }
}