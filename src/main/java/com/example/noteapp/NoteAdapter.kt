package com.example.noteapp

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NoteRowBinding

class NoteAdapter(private val noteList: ArrayList<Note>,private val onLongClick: (Note) -> Unit) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    inner class NoteHolder(val binding: NoteRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val binding = NoteRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note = noteList[position]
        holder.binding.basliq.text = note.title
        holder.binding.ic.text = note.details

        val drawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 20f

            val colorResId = when (position % 6) {
                0 -> R.color.blue
                1 -> R.color.pink
                2 -> R.color.yellow
                3 -> R.color.green
                4 -> R.color.light_gray
                5 -> R.color.orange
                else -> R.color.white
            }
            setColor(ContextCompat.getColor(holder.itemView.context, colorResId))
        }

        holder.binding.root.background = drawable

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, Details::class.java)
            intent.putExtra("id", note.id)
            intent.putExtra("info", "old")
            holder.itemView.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            onLongClick(note)
            true
        }

    }

    override fun getItemCount(): Int {
        return noteList.size
    }

}