package com.example.stulo_a

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MainActivity2 : AppCompatActivity() {
    var events: ArrayList<Event> = ArrayList()
    //registerForActivity wird für die Kommunikation zwischen Activities verwendet
    val someActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        //von Activity3 kriegt man die Daten in Form von Intent
        if (result.resultCode == 0) {
            val intent = result.data
            if (intent != null) {
                //ArrayList wird erneuert, wenn ein Event in MainActivity3 gelöscht wird
                events = intent.getSerializableExtra("events") as ArrayList<Event>
                save()
            }
        } else {
            println("keine Daten")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val show = findViewById<Button>(R.id.show)
        val create = findViewById<Button>(R.id.create)
        val calendar = findViewById<CalendarView>(R.id.calendarView)
        var date_ = ""
        //setOnDateChangeListener wird für die Veränderung des Datums verwendet
        calendar.setOnDateChangeListener { calendarView, i, i2, i3 ->
            val calendar_format = "$i3" + "/" + (i2 + 1) + "/" + "$i"
            Log.d(TAG, "Datum: " + calendar_format)
            date_ = calendar_format
        }
        create.setOnClickListener{
            create_event(date_)
        }
        show.setOnClickListener {
            show_event()
            load()
        }
    }
    //Die Funktion für den Speicher von Events
    fun save() {
        val pref = applicationContext.getSharedPreferences("MyPref", 0) // 0 - for private mode
        //Für die Änderung von Daten wird noch Editor verwendet
        val editor: SharedPreferences.Editor = pref.edit()
        val gson = Gson()
        //Umwandlung in gson
        val json = gson.toJson(events)
        val saved = editor.putString("event", json)
        println("saved" + saved)
        editor.apply()
    }
    //Die Funktion für Herunterladen von Events
    fun load() {
        val pref = applicationContext.getSharedPreferences("MyPref", 0) // 0 - for private mode
        val load = pref.getString("event", null)
        val gson = Gson()
        //Kovertierung von Gson in List von Objekten
        val type: Type = object : TypeToken<ArrayList<Event?>?>() {}.type
        val arrayItems = gson.fromJson<ArrayList<Event>>(load, type)
        events = arrayItems
    }
    //Die Funktion für die Erstellung von Events
    fun create_event(date_: String) {
        val name_employer = findViewById<EditText>(R.id.employer)
        val hours = findViewById<EditText>(R.id.hours)
        val salary = findViewById<EditText>(R.id.salary)

        val name = name_employer.text.toString()
        val stunden = hours.text.toString()
        val lohn = salary.text.toString()
        //Die Bedingung prüft, ob die Daten eingegeben wurden
        if (name > "" && stunden > "" && lohn > "" && date_ > "") {
            val a = stunden.toFloat()
            val b = lohn.toFloat()
            val sum = a * b
            val sum_string = sum.toString()
            //Die Erstellung des Objektes
            val events_ = Event(employer = "Arbeitgeber: $name",salary = "Gehalt: $lohn", hours = "Stunden: $stunden",
                result = "Gewinn: $sum_string", calendar = "Datum: $date_"
            )
            //Hinzufügen des Objektes in ArrayList
            events.add(events_)
            val activity_three = Intent(this, MainActivity3::class.java)
            //Die Daten werden mit putExtra-Methode gesendet
            activity_three.putExtra("events", events)
            //Mit Methode launch wird someActivityResultLauncher aktiviert
            someActivityResultLauncher.launch(activity_three)
        }
        else {
            //Toast wird gezeigt, wenn die Daten nicht eigegeben wurden
            Toast.makeText(this, "Keine Daten!", Toast.LENGTH_LONG).show()
        }
    }
    //Funktion für die Veranschaulichung von Events
    fun show_event() {
        load()
        //Die Überprüfung von Anwesenheit von Events
        if (events.isNotEmpty()) {
            val activityThree = Intent(this, MainActivity3::class.java)
            activityThree.putExtra("events", events)
            someActivityResultLauncher.launch(activityThree)
        } else {
            Toast.makeText(this, "Keine Events!", Toast.LENGTH_LONG).show()
        }
    }
}