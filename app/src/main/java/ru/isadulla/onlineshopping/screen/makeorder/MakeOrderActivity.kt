package ru.isadulla.onlineshopping.screen.makeorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.activity_make_order.*
import kotlinx.android.synthetic.main.appbar.view.*
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.android.synthetic.main.fragment_product_detail.appBar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import ru.isadulla.onlineshopping.MapsActivity
import ru.isadulla.onlineshopping.R
import ru.isadulla.onlineshopping.databinding.ActivityMakeOrderBinding
import ru.isadulla.onlineshopping.model.AddressModel
import ru.isadulla.onlineshopping.model.ProductModel
import ru.isadulla.onlineshopping.utils.Const

class MakeOrderActivity : AppCompatActivity() {
    var addressModel: AddressModel? = null
    lateinit var item: List<ProductModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_order)
        appBar.pop.setImageResource(R.drawable.ic_back)
        appBar.click_add.visibility = View.INVISIBLE
        appBar.pop.setOnClickListener { finish() }
        edit_address.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }


        item = intent.getSerializableExtra(Const.EXTRA_DATA) as List<ProductModel>
        total_amount.setText(item.sumByDouble {
            (it.price.replace(" ", "").toDoubleOrNull()) ?: 0.0 * it.cart_count.toDouble()
        }.toString())
    }

    override fun onDestroy() {
        super.onDestroy()

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe
    fun onEvent(address: AddressModel) {
        edit_address.setText("${address.latitude}, ${address.longitude}")

    }
}