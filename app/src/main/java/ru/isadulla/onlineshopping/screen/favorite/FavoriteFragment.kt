package ru.isadulla.onlineshopping.screen.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorite.*
import ru.isadulla.onlineshopping.R
import ru.isadulla.onlineshopping.databinding.FragmentFavoriteBinding
import ru.isadulla.onlineshopping.mvvm.MainViewModel
import ru.isadulla.onlineshopping.utils.PrefUtils
import ru.isadulla.onlineshopping.view.ProductAdapter

class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.productData.observe(this, Observer {
            binding.recyclerFavorite.adapter = ProductAdapter(it)
        })
        viewModel.progress.observe(this, Observer {
            binding.swipe.isRefreshing = it
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.bind(
            inflater.inflate(
                R.layout.fragment_favorite,
                container,
                false
            )

        )

        binding.appBar.title.text = "Favorites"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerFavorite.layoutManager = LinearLayoutManager(requireActivity())
        binding.swipe.setOnRefreshListener {
            loadData()
        }
        loadData()
    }

    fun loadData() {
        viewModel.getProductsByIds(PrefUtils.getFavoriteList())
    }

    companion object {

        fun newInstance() = FavoriteFragment()
    }
}