package com.danielbeleza.diagonalee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.danielbeleza.diagonalee.entity.Product
import com.danielbeleza.diagonalee.entity.ProductGenerator
import kotlinx.android.synthetic.main.feed_row.view.*

class MainFeedAdapter(
    private val content: List<Product> = mutableListOf(),
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<MainFeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.feed_row, parent, false).rootView
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = content.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = content[position]
        with(holder.itemView) {
            product_title.text = product.title
            product_subtitle.text = product.subtitle
            price.text = "${product.priceWithDiscount}"

            setOnClickListener {
                onClick(content[position])
                Toast.makeText(context, "Click event triggered", Toast.LENGTH_SHORT).show()
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}