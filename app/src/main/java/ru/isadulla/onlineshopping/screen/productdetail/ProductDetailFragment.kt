package ru.isadulla.onlineshopping.screen.productdetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.appbar.view.*
import kotlinx.android.synthetic.main.fragment_product_detail.*
import ru.isadulla.onlineshopping.R
import ru.isadulla.onlineshopping.databinding.FragmentProductDetailBinding
import ru.isadulla.onlineshopping.model.ProductModel
import ru.isadulla.onlineshopping.utils.Const
import ru.isadulla.onlineshopping.utils.PrefUtils

class ProductDetailFragment : AppCompatActivity() {
    lateinit var item: ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_product_detail)

        appBar.pop.setImageResource(R.drawable.ic_back)
        appBar.add.setImageResource(R.drawable.ic_heart)
        appBar.pop.setOnClickListener {

        }
        appBar.add.setOnClickListener {
            PrefUtils.setFavorite(item)
            if (PrefUtils.checkFavorite(item)) {
                appBar.add.setImageResource(R.drawable.ic_favorite)
            } else {
                appBar.add.setImageResource(R.drawable.ic_heart)
            }
        }

        item = intent.getSerializableExtra(Const.EXTRA_DATA) as ProductModel
        product_name.text = item.name
        appBar.title.text = item.name
        product_price.text = item.price
        if (PrefUtils.checkFavorite(item)) {
            appBar.add.setImageResource(R.drawable.ic_favorite)
        } else {
            appBar.add.setImageResource(R.drawable.ic_heart)
        }

        if (PrefUtils.getCartCount(item) > 0) {
            add_to_cart.visibility = View.GONE
        }
        Glide.with(this).load(Const.HOST_IMAGE + item.image).into(product_image)

        add_to_cart.setOnClickListener {
            item.cart_count = 1
            PrefUtils.setCart(item)
            Toast.makeText(this, "Product qoshildi", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}