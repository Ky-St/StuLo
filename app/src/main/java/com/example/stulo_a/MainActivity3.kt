package com.example.stulo_a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import com.example.myapplication.RecipeAdapter

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        //Die Daten von MainActivity2 zu MainActivity3 werden mit Intent realisiert
        val events_: ArrayList<Event> = intent.getSerializableExtra("events") as ArrayList<Event>

        val listview = findViewById<ListView>(R.id.mylistview)
        //Adapter dient als Vermittler zwischen ListView und Array
        val adapter = RecipeAdapter(this, events_)
        listview.adapter = adapter

        val intent = Intent()
        intent.putExtra("events", events_)
        //
        setResult(0, intent)
    }
}