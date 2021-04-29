package ru.isadulla.onlineshopping.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_item.view.*
import ru.isadulla.onlineshopping.R
import ru.isadulla.onlineshopping.model.ProductModel
import ru.isadulla.onlineshopping.screen.productdetail.ProductDetailFragment
import ru.isadulla.onlineshopping.utils.Const

class ProductAdapter(val items: List<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ItemHolder>() {
    class ItemHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ProductDetailFragment::class.java)
            intent.putExtra(Const.EXTRA_DATA, item)
            it.context.startActivity(intent)
        }

        Glide.with(holder.itemView.context).load(Const.HOST_IMAGE + item.image)
            .into(holder.itemView.imgProduct)
        holder.itemView.product_name.text = item.name
        holder.itemView.product_price.text = item.price
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}