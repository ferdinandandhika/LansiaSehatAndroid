package com.example.lansiasehat.ui.olahraga

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lansiasehat.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView

class DetailActivity : AppCompatActivity() {
    private var player: ExoPlayer? = null
    private lateinit var playerView: PlayerView
    private lateinit var progressBar: ProgressBar
    private lateinit var detailTextView: TextView
    private lateinit var detailKegiatanTextView: TextView
    private lateinit var btnNextStep: Button
    private lateinit var btnPreviousStep: Button
    private lateinit var btnHalodoc : TextView

    private var currentPosition: Int = -1
    private lateinit var detailArray: Array<String>
    private lateinit var definisiArray: Array<String>
    private lateinit var detailPendinginan: Array<String>
    private lateinit var definisiPendinginan: Array<String>
    private lateinit var videoUrls: Array<String>
    private var source: String? = null

    private lateinit var judulArray: Array<String>
    private lateinit var gambarArray: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)

        playerView = findViewById(R.id.player_view)
        progressBar = findViewById(R.id.progress_bar)
        detailTextView = findViewById(R.id.detail)
        detailKegiatanTextView = findViewById(R.id.detailKegiatan)
        btnNextStep = findViewById(R.id.btn_next_step)
        btnPreviousStep = findViewById(R.id.btn_previous_step)
        btnHalodoc = findViewById(R.id.agility)

        detailArray = resources.getStringArray(R.array.detail_array)
        definisiArray = resources.getStringArray(R.array.definisiOlahraga)
        detailPendinginan = resources.getStringArray(R.array.judulpendinginan_array)
        definisiPendinginan = resources.getStringArray(R.array.definisiPendinginan)

        videoUrls = intent.getStringArrayExtra("video_urls") ?: arrayOf()
        currentPosition = intent.getIntExtra("POSITION", -1)
        source = intent.getStringExtra("SOURCE")

        when (source) {
            "Set1Activity" -> {
                judulArray = resources.getStringArray(R.array.judulset1_array)
                gambarArray = resources.obtainTypedArray(R.array.gambarset1_array).let { typedArray ->
                    IntArray(typedArray.length()) { i -> typedArray.getResourceId(i, -1) }
                }
                definisiArray = resources.getStringArray(R.array.definisiset1_array)
            }
            "Set2Activity" -> {
                judulArray = resources.getStringArray(R.array.judulset2_array)
                gambarArray = resources.obtainTypedArray(R.array.gambarset2_array).let { typedArray ->
                    IntArray(typedArray.length()) { i -> typedArray.getResourceId(i, -1) }
                }
                definisiArray = resources.getStringArray(R.array.definisiset2_array)
            }
            "Set3Activity" -> {
                judulArray = resources.getStringArray(R.array.judulset3_array)
                gambarArray = resources.obtainTypedArray(R.array.gambarset3_array).let { typedArray ->
                    IntArray(typedArray.length()) { i -> typedArray.getResourceId(i, -1) }
                }
                definisiArray = resources.getStringArray(R.array.definisiset3_array)
            }
            else -> {
                Log.e("DetailActivity", "Unknown source: $source")
            }
        }

        // Add log to check the values of video_urls
        Log.d("DetailActivity", "video_urls: ${videoUrls.joinToString()}")

        if (currentPosition != -1) {
            updateDetail(currentPosition)
            initializePlayer(videoUrls[currentPosition])
            updateButtonVisibility()
        } else {
            Log.e("DetailActivity", "Position is -1")
        }

        btnHalodoc.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ageility.com/ageility-blog/30-minute-workout-for-seniors/"))
            startActivity(intent)
        }

        btnNextStep.setOnClickListener {
            if (source == "PemanasanActivity" && currentPosition < detailArray.size - 1) {
                currentPosition++
                updateDetail(currentPosition)
                updatePlayer(videoUrls[currentPosition])
                updateButtonVisibility()
            } else if (source == "PendinginanActivity" && currentPosition < detailPendinginan.size - 1) {
                currentPosition++
                updateDetail(currentPosition)
                updatePlayer(videoUrls[currentPosition])
                updateButtonVisibility()
            } else if (source?.startsWith("Set") == true && currentPosition < judulArray.size - 1) {
                currentPosition++
                updateDetail(currentPosition)
                updatePlayer(videoUrls[currentPosition])
                updateButtonVisibility()
            }
        }

        btnPreviousStep.setOnClickListener {
            if (currentPosition > 0) {
                currentPosition--
                updateDetail(currentPosition)
                updatePlayer(videoUrls[currentPosition])
                updateButtonVisibility()
            }
        }

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updateDetail(position: Int) {
        when (source) {
            "PemanasanActivity" -> {
                if (position in detailArray.indices && position in definisiArray.indices) {
                    detailTextView.text = detailArray[position]
                    detailKegiatanTextView.text = definisiArray[position]
                } else {
                    Log.e("DetailActivity", "Invalid position: $position")
                }
            }
            "PendinginanActivity" -> {
                if (position in detailPendinginan.indices && position in definisiPendinginan.indices) {
                    detailTextView.text = detailPendinginan[position]
                    detailKegiatanTextView.text = definisiPendinginan[position]
                } else {
                    Log.e("DetailActivity", "Invalid position: $position")
                }
            }
            "Set1Activity", "Set2Activity", "Set3Activity" -> {
                if (position in judulArray.indices && position in definisiArray.indices) {
                    detailTextView.text = judulArray[position]
                    detailKegiatanTextView.text = definisiArray[position]
                } else {
                    Log.e("DetailActivity", "Invalid position: $position")
                }
            }
            else -> {
                Log.e("DetailActivity", "Unknown source: $source")
            }
        }
    }

    private fun initializePlayer(videoUrl: String) {
        try {
            player = ExoPlayer.Builder(this).build().also { exoPlayer ->
                playerView.player = exoPlayer

                val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
                exoPlayer.playWhenReady = true

                exoPlayer.addListener(object : Player.Listener {
                    override fun onIsLoadingChanged(isLoading: Boolean) {
                        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                    }
                })
            }
        } catch (e: IllegalArgumentException) {
            Log.e("DetailActivity", "Error initializing player: ${e.message}", e)
        } catch (e: Exception) {
            Log.e("DetailActivity", "Error initializing player", e)
        }
    }

    private fun updatePlayer(videoUrl: String) {
        player?.let {
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
            it.setMediaItem(mediaItem)
            it.prepare()
            it.playWhenReady = true
        }
    }

    private fun updateButtonVisibility() {
        btnPreviousStep.visibility = if (currentPosition == 0) View.INVISIBLE else View.VISIBLE
        btnNextStep.visibility = if ((source == "PemanasanActivity" && currentPosition == detailArray.size - 1) ||
                                     (source == "PendinginanActivity" && currentPosition == detailPendinginan.size - 1) ||
                                     (source?.startsWith("Set") == true && currentPosition == judulArray.size - 1)) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }
}
