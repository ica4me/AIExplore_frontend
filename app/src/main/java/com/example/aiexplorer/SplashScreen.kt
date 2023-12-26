package com.example.aiexplorer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    private val splashTimeOut: Long = 2000 // Durasi splash screen 3 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            // Intent yang akan membuka MainActivity setelah waktu timeout
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Mengakhiri activity ini agar tidak kembali ke splash screen
        }, splashTimeOut)
    }
}