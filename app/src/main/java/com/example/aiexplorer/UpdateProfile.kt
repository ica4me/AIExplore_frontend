package com.example.aiexplorer

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class UpdateProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            // Implement your logic to save/update profile here
        }

        // Add listeners for other buttons if needed
    }
}
