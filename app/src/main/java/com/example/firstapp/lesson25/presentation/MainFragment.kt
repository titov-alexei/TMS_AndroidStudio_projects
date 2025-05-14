package com.example.firstapp.lesson25.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentMainBinding
import com.example.firstapp.lesson25.domain.LoadState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.cancelRequest()
        _binding = null
    }

    private fun setupView() {
        binding.btnDownloadData.setOnClickListener {
            viewModel.fetchData()
        }
    }

    private fun setupObservers() {
        viewModel.loadingState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadState.Loading -> {
                    binding.textResultDownload.text = getString(R.string.data_loading)
                    binding.progressBarDownload.visibility = View.VISIBLE
                }
                is LoadState.Success -> {
                    binding.textResultDownload.text = state.data
                    binding.progressBarDownload.visibility = View.GONE
                }
                is LoadState.Error -> {
                    binding.textResultDownload.text = state.exception
                    binding.progressBarDownload.visibility = View.GONE
                }
                is LoadState.Initial -> {
                    binding.textResultDownload.text = ""
                    binding.progressBarDownload.visibility = View.GONE
                }
            }
        }
    }


}