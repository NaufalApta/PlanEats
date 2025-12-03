package com.myapplication.planeats.model

data class Meal(
    val id: Int = 0,
    val title: String,
    val calories: Int,
    val category: String,
    val imageRes: Int? = null
)