package com.emdp.pruebatecnica.mobgen.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.emdp.pruebatecnica.mobgen.common.Utils
import com.emdp.pruebatecnica.mobgen.databinding.AdapterHousesBinding
import com.emdp.pruebatecnica.mobgen.model.api.HousesResponse


class HousesAdapter(
    private val houses: List<HousesResponse>
) : RecyclerView.Adapter<HousesAdapter.ViewHolder>() {

    private val utils : Utils.Companion = Utils

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

        utils.showHideViewItems(holder.tvName, holder.tvHousesName, house.name)
        utils.showHideViewItems(holder.tvRegion, holder.tvHousesRegion, house.region)
        utils.showHideViewItems(holder.tvTitle, holder.tvHousesTitle, house.title)
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
        val tvName: TextView = binding.tvName
        val tvRegion: TextView = binding.tvRegion
        val tvTitle: TextView = binding.tvTitle

        val ivHouses: ImageView = binding.ivHouses
        val tvHousesName: TextView = binding.tvHousesName
        val tvHousesRegion: TextView = binding.tvHousesRegion
        val tvHousesTitle: TextView = binding.tvHousesTitle

        override fun toString(): String {
            return super.toString() + " '" + tvHousesName.text + "'" +
                    " '" + tvHousesRegion.text + "'" + " '" + tvHousesTitle.text + "'"
        }
    }
}