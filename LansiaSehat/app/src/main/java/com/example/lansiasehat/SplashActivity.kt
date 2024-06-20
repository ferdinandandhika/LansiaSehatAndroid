package com.example.lansiasehat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lansiasehat.ui.pager.PagerActivity
import com.example.lansiasehat.ui.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("com.example.lansiasehat", MODE_PRIVATE)
        val isFirstRun = sharedPreferences.getBoolean("isFirstRun", true)

        Handler(Looper.getMainLooper()).postDelayed({
            if (isFirstRun) {
                sharedPreferences.edit().putBoolean("isFirstRun", false).apply()
                // Start PagerActivity
                startActivity(Intent(this, PagerActivity::class.java))
            } else {
                // Start MainActivity
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }, 2000)
    }
}