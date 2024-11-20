package com.example.fakefitpass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NexttActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nextt)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val checkinDate = findViewById<TextView>(R.id.checkinDate)
        val checkinTime = findViewById<TextView>(R.id.checkinTime)

        // Get the current date and time
        val currentDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        // Set the current date and time to the TextViews
        checkinDate.text = currentDate
        checkinTime.text = currentTime

        val inputText = intent.getStringExtra("EXTRA_TEXT")
        val obieqti = intent.getStringExtra("OBIEQTI")
        val username = findViewById<TextView>(R.id.username)
        val obieqtiName = findViewById<TextView>(R.id.obieqtiName)
        username.text = inputText
        obieqtiName.text = obieqti


        val okbutton = findViewById<Button>(R.id.OkButton)
        okbutton.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
}