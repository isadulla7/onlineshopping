package ru.isadulla.onlineshopping.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_home.*
import ru.isadulla.onlineshopping.R
import ru.isadulla.onlineshopping.databinding.FragmentHomeBinding
import ru.isadulla.onlineshopping.model.CategoriesModel
import ru.isadulla.onlineshopping.model.OffersModel
import ru.isadulla.onlineshopping.mvvm.MainViewModel
import ru.isadulla.onlineshopping.utils.Const
import ru.isadulla.onlineshopping.view.CategoryAdapter
import ru.isadulla.onlineshopping.view.CategoryAdapterCallback
import ru.isadulla.onlineshopping.view.ProductAdapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    var offers: List<OffersModel> = emptyList()
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            FragmentHomeBinding.bind(inflater.inflate(R.layout.fragment_home, container, false))
        binding.appBar.title.text = "Home"
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefresh.setOnRefreshListener {
            loadData()
        }
        binding.recyclerCategory.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.topList.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        viewModel.error.observe(requireActivity(), Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        })
        viewModel.progress.observe(requireActivity(), Observer {
            binding.swipeRefresh.isRefreshing = it
        })
        viewModel.offersData.observe(requireActivity(), {
            binding.carouselView.setImageListener { position, imageView ->
                Glide.with(imageView)
                    .load(Const.HOST_IMAGE + it[position].image)
                    .into(imageView)
            }
            binding.carouselView.pageCount = offers.count()
        })

        viewModel.categoriesData.observe(this.requireActivity(), Observer {
            binding.recyclerCategory.adapter =
                CategoryAdapter(it, object : CategoryAdapterCallback {
                    override fun OnClickItem(item: CategoriesModel) {
                        viewModel.getProductsByCategory(item.id)
                    }
                })
        })
        viewModel.productData.observe(requireActivity(), Observer {
            top_list.adapter = ProductAdapter(it)
        })

        loadData()
    }

    fun loadData() {
        viewModel.getOffers()
        viewModel.getProducts()
        viewModel.getCategories()

    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}