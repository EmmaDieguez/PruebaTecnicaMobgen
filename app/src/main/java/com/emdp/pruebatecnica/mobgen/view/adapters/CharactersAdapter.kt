package com.emdp.pruebatecnica.mobgen.view.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.emdp.pruebatecnica.mobgen.common.Utils
import com.emdp.pruebatecnica.mobgen.databinding.AdapterCharactersBinding
import com.emdp.pruebatecnica.mobgen.model.api.CharactersResponse

class CharactersAdapter(
    private val context: Context,
    private val characters: List<CharactersResponse>
) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    private val utils : Utils.Companion = Utils

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

        utils.showHideViewItems(holder.tvName, holder.tvCharacterName, character.name)
        utils.showHideViewItems(holder.tvGender, holder.tvCharacterGender, character.gender)
        utils.showHideViewItems(holder.tvCulture, holder.tvCharacterCulture, character.culture)
        utils.showHideViewItems(holder.tvBorn, holder.tvCharactersBorn, character.born)
        utils.showHideViewItems(holder.tvDied, holder.tvCharactersDied, character.died)
        utils.showHideViewItems(holder.tvTitles, holder.llCharactersTitles, character.titles,
            context)
        utils.showHideViewItems(holder.tvAliases, holder.llCharactersAliases, character.aliases,
            context)
        utils.showHideViewItems(holder.tvFather, holder.tvCharactersFather, character.father)
        utils.showHideViewItems(holder.tvMother, holder.tvCharactersMother, character.mother)
        utils.showHideViewItems(holder.tvSpouse, holder.tvCharactersSpouse, character.spouse)
        utils.showHideViewItems(holder.tvAllegiances, holder.llCharactersAllegiances,
                character.allegiances, context)
        utils.showHideViewItems(holder.tvPlayedBy, holder.llCharactersPlayedBy, character.playedBy,
            context)
    }

    override fun getItemCount(): Int = characters.size

    inner class ViewHolder(binding: AdapterCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val tvName: TextView = binding.tvName
        val tvGender: TextView = binding.tvGender
        val tvCulture: TextView = binding.tvCulture
        val tvBorn: TextView = binding.tvBorn
        val tvDied: TextView = binding.tvDied
        val tvTitles: TextView = binding.tvTitles
        val tvAliases: TextView = binding.tvAliases
        val tvFather: TextView = binding.tvFather
        val tvMother: TextView = binding.tvMother
        val tvSpouse: TextView = binding.tvSpouse
        val tvAllegiances: TextView = binding.tvAllegiances
        val tvPlayedBy: TextView = binding.tvPlayedBy

        val tvCharacterName: TextView = binding.tvCharacterName
        val tvCharacterGender: TextView = binding.tvCharacterGender
        val tvCharacterCulture: TextView = binding.tvCharacterCulture
        val tvCharactersBorn: TextView = binding.tvCharactersBorn
        val tvCharactersDied: TextView = binding.tvCharactersDied
        val llCharactersTitles: LinearLayout = binding.llCharactersTitles
        val llCharactersAliases: LinearLayout = binding.llCharactersAliases
        val tvCharactersFather: TextView = binding.tvCharactersFather
        val tvCharactersMother: TextView = binding.tvCharactersMother
        val tvCharactersSpouse: TextView = binding.tvCharactersSpouse
        val llCharactersAllegiances: LinearLayout = binding.llCharactersAllegiances
        val llCharactersPlayedBy: LinearLayout = binding.llCharactersPlayedBy

        override fun toString(): String {
            return super.toString() + " '" + tvCharacterName.text + "'" +
                    " '" + tvCharacterGender.text + "'" +
                    " '" + tvCharacterCulture.text + "'" +
                    " '" + tvCharactersBorn.text + "'" +
                    " '" + tvCharactersDied.text + "'" +
                    " '" + tvCharactersFather.text + "'" +
                    " '" + tvCharactersMother.text + "'" +
                    " '" + tvCharactersSpouse.text + "'"
        }
    }

}