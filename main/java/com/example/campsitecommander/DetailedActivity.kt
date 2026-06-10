package com.example.campsitecommander

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed)

        // Handle window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtDetails = findViewById<TextView>(R.id.txtDetails)
        val btnBack = findViewById<Button>(R.id.BacktoBase)

        // Retrieve data from Intent
        val item = intent.getStringArrayListExtra("item")
        val category = intent.getStringArrayListExtra("category")
        val quantity = intent.getDoubleArrayExtra("quantity")
        val comments = intent.getStringArrayListExtra("comments")

        var display = ""

        // Check if data is not null and arrays have the same size
        if (item != null && category != null && quantity != null && comments != null) {
            for (i in item.indices) {
                display += "Item: ${item[i]}\n" +
                        "Category: ${category[i]}\n" +
                        "Quantity: ${quantity[i]}\n" +
                        "Comments: ${comments[i]}\n\n"
            }
        } else {
            display = "No gear data available."
        }

        txtDetails.text = display

        // Return to previous screen
        btnBack.setOnClickListener {
            finish()
        }
    }
}