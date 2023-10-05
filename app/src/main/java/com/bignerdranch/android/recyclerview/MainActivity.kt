package com.bignerdranch.android.recyclerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class ColorData(var colorName: String, var hex: Int)

class MyAdapter(private var colors: List<ColorData>, val listener: Listener) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorView: View = itemView.findViewById(R.id.view)
        val colorNameView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.colorView.setBackgroundColor(colors[position].hex)
        holder.colorNameView.text = colors[position].colorName

        holder.colorView.setOnClickListener {
            listener.onCellClickListener(colors[position])
        }
    }

    interface Listener {
        fun onCellClickListener(color: ColorData)
    }
}

class MainActivity : AppCompatActivity(), MyAdapter.Listener {
    private lateinit var recyclerView: RecyclerView;

    private val colors = mutableListOf<ColorData>(
        ColorData("BLACK", Color.parseColor("#000000")),
        ColorData("YELLOW", Color.parseColor("#FFFF00")),
        ColorData("BLUE", Color.parseColor("#0000FF")),
        ColorData("RED", Color.parseColor("#FF0000")),
        ColorData("GREEN", Color.parseColor("#008000")),
        ColorData("PURPLE", Color.parseColor("#800080")),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter(colors, this)
    }

    override fun onCellClickListener(color: ColorData) {
        Toast.makeText(
            this,
            "IT'S ${color.colorName}",
            Toast.LENGTH_LONG
        ).show()
    }
}