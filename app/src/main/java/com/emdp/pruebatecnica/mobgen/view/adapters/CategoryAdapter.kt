package com.emdp.pruebatecnica.mobgen.view.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.emdp.pruebatecnica.mobgen.databinding.AdapterCategoryBinding
import com.emdp.pruebatecnica.mobgen.listeners.OnCategoryClickListener
import com.emdp.pruebatecnica.mobgen.model.database.Categories

class CategoryAdapter(
    private var categories: List<Categories>
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var listener : OnCategoryClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            AdapterCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]

        holder.contentView.text = category.categoryName

        holder.itemView.setOnClickListener {
            listener?.onItemClicked(category)
        }
    }

    override fun getItemCount(): Int = categories.size

    inner class ViewHolder(binding: AdapterCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    fun setOnClickListener(clickListener: OnCategoryClickListener) {
        listener = clickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshAdapter(categoriesList: List<Categories>) {
        categories = categoriesList
        this.notifyDataSetChanged()
    }
}