package com.example.noteapp

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.CategoriesRowBinding

class CategoriesAdapter(val categoryList: ArrayList<Categories>) : RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {
    class CategoriesHolder(val binding: CategoriesRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val binding = CategoriesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.binding.name.text = categoryList.get(position).name

        if (position == selectedPosition) {
            holder.binding.name.setTextColor(Color.WHITE)
            holder.binding.root.background = getBackgroundDrawable(Color.BLACK)

        } else {
            holder.binding.name.setTextColor(Color.BLACK)

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
            cornerRadius = 30f
            setColor(color)
        }
    }

    }
