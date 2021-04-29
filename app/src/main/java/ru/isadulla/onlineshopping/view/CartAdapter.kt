package ru.isadulla.onlineshopping.view

import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cart_item.view.*
import ru.isadulla.onlineshopping.R
import ru.isadulla.onlineshopping.model.ProductModel
import ru.isadulla.onlineshopping.utils.Const

class CartAdapter(val items: List<ProductModel>) : RecyclerView.Adapter<CartAdapter.ItemHolder>() {

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]

        holder.itemView.product_price.text = item.price
        holder.itemView.product_name.text = item.name

        holder.itemView.img_minus.setOnClickListener {
            item.cart_count--
            holder.itemView.product_count.text = item.cart_count.toString()
            notifyDataSetChanged()
        }
        holder.itemView.img_plus.setOnClickListener {
            item.cart_count++
            holder.itemView.product_count.text = item.cart_count.toString()
            notifyDataSetChanged()
        }

        Glide.with(holder.itemView).load(Const.HOST_IMAGE + item.image)
            .into(holder.itemView.imgProduct)

        holder.itemView.product_count.text = item.cart_count.toString()

    }

    override fun getItemCount(): Int {
        return items.count()
    }
}