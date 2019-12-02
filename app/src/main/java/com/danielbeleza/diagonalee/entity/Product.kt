package com.danielbeleza.diagonalee.entity

data class Product(
    val id: Int,
    val name: String,
    val title: String,
    val subtitle: String,
    val price: Double,
    val discount: Double
) {
    val priceWithDiscount: Double = price - discount
}

object ProductGenerator {
    fun generateProducts(quantity: Int = 1): List<Product> {
        val productsList = mutableListOf<Product>()
        repeat(quantity) {
            productsList.add(
                Product(
                    id = it,
                    name = "Product ${it + 1}",
                    title = "$it$it || Title || $it$it",
                    subtitle = "Product $it is...",
                    price = (10..30).random().toDouble(),
                    discount = (0..5).random().toDouble()
                )
            )
        }
        return productsList
    }
}