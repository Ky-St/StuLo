package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.stulo_a.Event
import com.example.stulo_a.R
//RecipeAdapter dient zum Herunterladen von Daten aus Array
class RecipeAdapter (context: Context, val datasource: ArrayList<Event>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        //getCount gibt die Anzahl von Elementen in Array zurück
        return datasource.size
    }

    override fun getItem(position: Int): Any {
        //getItem gibt die Position von Element in Array zurück
        return datasource.get(position)
    }

    override fun getItemId(position: Int): Long {
        //getItemId gibt ID für jede Zeile in Array zurück
        return position.toLong()
    }
    //mit getView wird festgestellt, welche Information und wo sich in ListView befinden soll
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.cardview, parent, false)

        val employer = rowView.findViewById(R.id.name_of_employer) as TextView
        val salary = rowView.findViewById(R.id.salary) as TextView
        val hours = rowView.findViewById(R.id.hours) as TextView
        val result = rowView.findViewById(R.id.result) as TextView
        val calendar = rowView.findViewById(R.id.calendar) as TextView
        val imageView = rowView.findViewById<ImageView>(R.id.delete) as ImageView
        imageView.tag = position

        val events_ = getItem(position) as Event
        employer.text = events_.employer
        salary.text = events_.salary
        hours.text = events_.hours
        result.text = events_.result
        calendar.text = events_.calendar
        //Das Löschen von Event mit dem Klick auf Button
        imageView.setOnClickListener{
            val selected = datasource.get(it.tag as Int)
            datasource.remove(selected)
            notifyDataSetChanged()
        }
        return rowView
    }
}