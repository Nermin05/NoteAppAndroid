package com.example.noteapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Date
import com.example.noteapp.databinding.DateRowBinding


class DateAdapter(val datelist: ArrayList<Date>) : RecyclerView.Adapter<DateAdapter.DateHolder>() {
    class DateHolder(val binding: DateRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateHolder {
        val binding = DateRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DateHolder(binding)
    }

    override fun getItemCount(): Int {
        return datelist.size
    }

    override fun onBindViewHolder(holder: DateHolder, position: Int) {
        holder.binding.gun.text = datelist.get(position).gun
        holder.binding.tarix.text = datelist.get(position).tarix
        holder.binding.ay.text = datelist.get(position).ay

    }
}