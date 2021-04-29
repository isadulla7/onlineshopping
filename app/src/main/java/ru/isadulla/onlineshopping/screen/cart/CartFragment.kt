package ru.isadulla.onlineshopping.screen.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_make_order.*
import kotlinx.android.synthetic.main.appbar.view.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.make_order
import ru.isadulla.onlineshopping.R
import ru.isadulla.onlineshopping.databinding.CartItemBinding
import ru.isadulla.onlineshopping.databinding.FragmentCartBinding
import ru.isadulla.onlineshopping.model.ProductModel
import ru.isadulla.onlineshopping.mvvm.MainViewModel
import ru.isadulla.onlineshopping.screen.makeorder.MakeOrderActivity
import ru.isadulla.onlineshopping.utils.Const
import ru.isadulla.onlineshopping.utils.PrefUtils
import ru.isadulla.onlineshopping.view.CartAdapter
import java.io.Serializable

@Suppress("UNREACHABLE_CODE")
class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.progress.observe(this, Observer {
            binding.swipe.isRefreshing = it
        })

        viewModel.error.observe(requireActivity(), Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        })

        viewModel.productData.observe(this, Observer {
            binding.cartRecycler.adapter = CartAdapter(it)
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentCartBinding.bind(
                inflater.inflate(
                    R.layout.fragment_cart,
                    container,
                    false
                )
            )

        binding.appBar.title.text = "Carts"

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cartRecycler.layoutManager = LinearLayoutManager(requireActivity())

        binding.makeOrder.setOnClickListener {
            startActivity(Intent(requireActivity(), MakeOrderActivity::class.java))
            val intent = Intent(requireActivity(), MakeOrderActivity::class.java)
            intent.putExtra(
                Const.EXTRA_DATA,
                (viewModel.productData.value ?: emptyList())
                        as Serializable
            )
            startActivity(intent)
        }


        binding.swipe.setOnRefreshListener {
            loadData()
        }
        loadData()

    }

    fun loadData() {
        viewModel.getProductsByIds(PrefUtils.getCartList().map { it.product_id })
    }

    companion object {

        fun newInstance() = CartFragment()
    }
}