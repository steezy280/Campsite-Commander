package com.example.campsitecommander

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val itemsList = ArrayList<String>()
    private val categoryList = ArrayList<String>()
    private val quantityList = ArrayList<Double>()
    private val commentsList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI components
        val edtItem = findViewById<EditText>(R.id.edtItem)
        val edtCategory = findViewById<EditText>(R.id.edtCategory)
        val edtQuantity = findViewById<EditText>(R.id.edtQuantity)
        val edtComments = findViewById<EditText>(R.id.edtComments)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnAddGear = findViewById<Button>(R.id.btnAddGear)
        val btnExit = findViewById<Button>(R.id.btnExit)

        // Button to add item to the lists
        btnAdd.setOnClickListener {
            val item = edtItem.text.toString()
            val category = edtCategory.text.toString()
            val quantityStr = edtQuantity.text.toString()
            val comments = edtComments.text.toString()

            if (item.isNotEmpty() && category.isNotEmpty() && quantityStr.isNotEmpty() && comments.isNotEmpty()) {
                try {
                    val quantity = quantityStr.toDouble()

                    itemsList.add(item)
                    categoryList.add(category)
                    quantityList.add(quantity)
                    commentsList.add(comments)

                    Toast.makeText(this, "Item added successfully!", Toast.LENGTH_SHORT).show()

                    // Clear fields for next entry
                    edtItem.text.clear()
                    edtCategory.text.clear()
                    edtQuantity.text.clear()
                    edtComments.text.clear()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Quantity must be a number", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Button to view details (Pass data to DetailedActivity)
        btnAddGear.setOnClickListener {
            if (itemsList.isEmpty()) {
                Toast.makeText(this, "No items to show", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, DetailedActivity::class.java)
                intent.putStringArrayListExtra("item", itemsList)
                intent.putStringArrayListExtra("category", categoryList)
                intent.putExtra("quantity", quantityList.toDoubleArray())
                intent.putStringArrayListExtra("comments", commentsList)
                startActivity(intent)
            }
        }

        // Exit button
        btnExit.setOnClickListener {
            finish()
        }
    }
}
