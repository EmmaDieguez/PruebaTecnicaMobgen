package com.emdp.pruebatecnica.mobgen.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.emdp.pruebatecnica.mobgen.databinding.AdapterHousesBinding
import com.emdp.pruebatecnica.mobgen.model.api.HousesResponse


class HousesAdapter(
    private val houses: List<HousesResponse>
) : RecyclerView.Adapter<HousesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            AdapterHousesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val house = houses[position]
        val url = loadUrl(house.region)

        if (null != url) {
            holder.ivHouses.visibility = View.VISIBLE

            Glide
                .with(holder.itemView.context)
                .load(url)
                .fitCenter()
                .into(holder.ivHouses)
        } else {
            holder.ivHouses.visibility = View.GONE
        }

        holder.tvHousesName.text = house.name
        holder.tvHousesRegion.text = house.region

        if (null != house.title && "" != house.title.trim()) {
            holder.tvTitle.visibility = View.VISIBLE
            holder.tvHousesTitle.visibility = View.VISIBLE

            holder.tvHousesTitle.text = house.title
        } else {
            holder.tvTitle.visibility = View.GONE
            holder.tvHousesTitle.visibility = View.GONE
        }
    }

    private fun loadUrl(region: String): String? {
        var url: String? = null

        when(region) {
            "The North" -> url = "https://bit.ly/2Gcq0r4"
            "The Vale" -> url = "https://bit.ly/34FAvws"
            "The Riverlands OR Iron Islands" -> url = "https://bit.ly/3kJrIiP"
            "The Westerlands" -> url = "https://bit.ly/2TAzjnO"
            "The Reach" -> url = "https://bit.ly/2HSCW5N"
            "Dorne" -> url = "https://bit.ly/2HOcavT"
            "The Stormlands" -> url = "https://bit.ly/34F2sEC"
        }
        return url
    }

    override fun getItemCount(): Int = houses.size

    inner class ViewHolder(binding: AdapterHousesBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivHouses: ImageView = binding.ivHouses
        val tvHousesName: TextView = binding.tvHousesName
        val tvHousesRegion: TextView = binding.tvHousesRegion
        val tvHousesTitle: TextView = binding.tvHousesTitle
        val tvTitle: TextView = binding.tvTitle

        override fun toString(): String {
            return super.toString() + " '" + tvHousesName.text + "'" +
                    " '" + tvHousesRegion.text + "'" + " '" + tvHousesTitle.text + "'"
        }
    }
}