package com.example.noteapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Date
import com.example.noteapp.databinding.DateRowBinding
import com.example.noteapp.databinding.NoteRowBinding


class NoteAdapter(private val items: List<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.basliq)
        val content: TextView = view.findViewById(R.id.ic)
        val layout: ConstraintLayout = view as ConstraintLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.basliq
        holder.content.text = item.ic
        holder.layout.setBackgroundColor(item.backgroundColor)
    }

    override fun getItemCount() = items.size
}