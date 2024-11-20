package com.example.fakefitpass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editText = findViewById<EditText>(R.id.saxeliText)
        val fitlandButton = findViewById<Button>(R.id.fitlandButton)
        val arenaButton = findViewById<Button>(R.id.arenaButton)

        fitlandButton.setOnClickListener {
            val inputText = editText.text.toString()
            val obieqti = "Fitland • ფიტლენდი"
            val intent = Intent(this, Scanner::class.java)
            intent.putExtra("EXTRA_TEXT", inputText)
            intent.putExtra("OBIEQTI", obieqti)
            startActivity(intent)
        }


        arenaButton.setOnClickListener {

            val inputText = editText.text.toString()
            val obieqti = "Leadersport Arena • " +
                    "ლიდერსპორტ არენა"
            val intent = Intent(this, Scanner::class.java)
            intent.putExtra("EXTRA_TEXT", inputText)
            intent.putExtra("OBIEQTI", obieqti)
            startActivity(intent)


        }





    }

}