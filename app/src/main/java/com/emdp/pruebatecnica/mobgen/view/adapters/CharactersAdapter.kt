package com.emdp.pruebatecnica.mobgen.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.emdp.pruebatecnica.mobgen.databinding.AdapterCharactersBinding
import com.emdp.pruebatecnica.mobgen.model.api.CharactersResponse

class CharactersAdapter(
    private val characters: List<CharactersResponse>
) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            AdapterCharactersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.idView.text = character.name
        holder.contentView.text = character.gender
    }

    override fun getItemCount(): Int = characters.size

    inner class ViewHolder(binding: AdapterCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}