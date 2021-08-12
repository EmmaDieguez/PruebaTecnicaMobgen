package com.emdp.pruebatecnica.mobgen.listeners

import com.emdp.pruebatecnica.mobgen.model.database.Categories

interface OnCategoryClickListener {
    fun onItemClicked(category : Categories)
}