package com.example.noteapp

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.DateRowBinding

class DateAdapter(val datelist: ArrayList<Date>) : RecyclerView.Adapter<DateAdapter.DateHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class DateHolder(val binding: DateRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateHolder {
        val binding = DateRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DateHolder(binding)
    }

    override fun getItemCount(): Int {
        return datelist.size
    }

    override fun onBindViewHolder(holder: DateHolder, position: Int) {
        holder.binding.gun.text = datelist[position].gun
        holder.binding.tarix.text = datelist[position].tarix
        holder.binding.ay.text = datelist[position].ay

        if (position == selectedPosition) {
            holder.binding.gun.setTextColor(Color.WHITE)
            holder.binding.tarix.setTextColor(Color.WHITE)
            holder.binding.ay.setTextColor(Color.WHITE)
            holder.binding.root.background = getBackgroundDrawable(Color.BLACK)

        } else {
            holder.binding.gun.setTextColor(Color.BLACK)
            holder.binding.tarix.setTextColor(Color.BLACK)
            holder.binding.ay.setTextColor(Color.BLACK)
            holder.binding.root.background = getBackgroundDrawable(Color.WHITE)
        }

        holder.itemView.setOnClickListener {
            notifyItemChanged(selectedPosition)
            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
        }
    }

    private fun getBackgroundDrawable(color: Int): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 50f
            setColor(color)
        }
    }
}
