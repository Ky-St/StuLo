package com.example.stulo_a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Es wird hier eine Methode findViewById verwendet, um die Button-Referenz und Id zu erhalten.
        //Und ich weise diese Referenz der "open_second_layout" Variablen zu.
        val imageButton = findViewById<ImageButton>(R.id.imageButton)

        //setOnClickListener-Methode verbindet imageButton mit der Funktion
        //Mit dieser Funktion wird das zweite Layout aufgemacht
        imageButton.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}