package com.example.firstapp.lesson29.presentation.fragment

import android.app.AlertDialog
import com.example.firstapp.lesson29.presentation.adapter.ProductAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.databinding.FragmentProductBinding
import com.example.firstapp.lesson29.presentation.FakeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FakeViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    private var cartCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
        viewModel.loadProducts()
        updateCartCountText()
    }

    private fun setupRecyclerView() {
        adapter = ProductAdapter(
            onItemClick = { product ->
                showDescription(product.description)
            },
            onAddToCart = { productId ->
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.addToCart(productId)
                    cartCount++ // Увеличиваем локальный счетчик
                    updateCartCountText() // Обновляем UI
                }
            })
        binding.recyclerViewProducts.adapter = adapter
        binding.recyclerViewProducts.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showDescription(description: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(description)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun updateCartCountText() {
        binding.inCart.text = "In cart: $cartCount" // Обновляем TextView
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.products.collect { products ->
                adapter.submitList(products)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}





































    /*private lateinit var recyclerViewProducts: RecyclerView
    private lateinit var adapter: FakeAdapter
    private val viewModel: FakeViewModel by viewModels()
    private lateinit var btnLoad: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        *//*recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts)
        adapter = FakeAdapter(emptyList()) { id ->
            //Обработка клика по продукту
        }
        recyclerViewProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ProductFragment.adapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.products.collect { products ->
                Log.d("MYLOG", "-------${products.size}")
                adapter.products = products
                adapter.notifyDataSetChanged()
            }
        }*//*

        btnLoad = view.findViewById(R.id.btnLoadProducts)
        btnLoad.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val url = URL("https://fakestoreapi.com/products")
                    val connection = url.openConnection() as HttpsURLConnection
                    connection.connectTimeout = 5000
                    connection.readTimeout = 5000
                    val response = connection.inputStream.bufferedReader().readText()
                    Log.d("MYLOG", "GOOOD_____${response.take(100)}")  // Проверьте логи
                } catch (e: Exception) {
                    Log.e("MYLOG", "Error: ${e.message}")
                }
            }
        }


        *//*viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadProducts()
        }*//*
    }*/
