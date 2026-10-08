package dev.aziz.souq.data.model

data class Product(
    val id : Int,
    val name : String,
    val price : Double,
    val description : String,
    val image : String,
    val category : String,
    val rating : Double,
    val count : Int
)
