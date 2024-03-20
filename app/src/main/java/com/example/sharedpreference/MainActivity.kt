package com.example.sharedpreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import kotlin.random.Random
import android.content.Context
import android.content.SharedPreferences

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private lateinit var editText: EditText
    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_NAME = "myPrefs"
    private val IMAGE_KEY = "imageKey"
    private val TEXT_KEY = "textKey"

    private val imageArray = arrayOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.button)
        editText = findViewById(R.id.editText)

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        // Restore saved state
        val savedImageId = sharedPreferences.getInt(IMAGE_KEY, R.drawable.image1)
        val savedText = sharedPreferences.getString(TEXT_KEY, "")
        imageView.setImageResource(savedImageId)
        editText.setText(savedText)



        button.setOnClickListener {
            // Load a random image into the ImageView
            val randomNumber = Random.nextInt(imageArray.size)
            imageView.setImageResource(imageArray[randomNumber])
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        // Save the current state when activity is destroyed
        with(sharedPreferences.edit()) {
            putInt(IMAGE_KEY, imageView.tag as Int)
            putString(TEXT_KEY, editText.text.toString())
            apply()
        }
    }
}