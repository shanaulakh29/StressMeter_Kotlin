package com.example.gurshan_aulakh_stressmeter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gurshan_aulakh_stressmeter.databinding.ActivitySelectedImageBinding
import java.io.File

class SelectedImage : AppCompatActivity() {
    private lateinit var binding: ActivitySelectedImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        val selectedImageResId = intent.getIntExtra("selectedImageResId",-1)
        binding = ActivitySelectedImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView2.setImageResource(selectedImageResId)
        binding.submitButton.setOnClickListener {
            val file = File(filesDir,"stress_timestamp.csv")
            val timestampSeconds = System.currentTimeMillis()/1000
            val position = intent.getIntExtra("position",-1)
            val csvLine = "$timestampSeconds,$position\n"
            file.appendText(csvLine)
            finishAffinity()
        }
        binding.cancelButton.setOnClickListener {
            finish()
        }

    }
}