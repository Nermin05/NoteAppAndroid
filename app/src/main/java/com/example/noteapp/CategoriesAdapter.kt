package com.example.noteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.CategoriesRowBinding

class CategoriesAdapter(val categoryList: ArrayList<Categories>) : RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {
    class CategoriesHolder(val binding: CategoriesRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val binding = CategoriesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.binding.name.text = categoryList.get(position).name

    }
}