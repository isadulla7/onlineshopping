package ru.isadulla.onlineshopping.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.category_items.view.*
import ru.isadulla.onlineshopping.R
import ru.isadulla.onlineshopping.model.CategoriesModel

interface CategoryAdapterCallback{
    fun OnClickItem(item: CategoriesModel)
}

class CategoryAdapter(val items: List<CategoriesModel>, val callback: CategoryAdapterCallback) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.category_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener {
            items.forEach{
                it.checked=false
            }
            callback.OnClickItem(item)
            item.checked=true
            notifyDataSetChanged()
        }
        holder.itemView.tvTitle.text = item.title

        if (item.checked) {
            holder.itemView.card_view.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.colorPrimary
                )
            )
            holder.itemView.tvTitle.setTextColor(Color.WHITE)
        } else {
            holder.itemView.card_view.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.grey
                )
            )
            holder.itemView.tvTitle.setTextColor(Color.BLACK)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

}