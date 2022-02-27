package com.example.trendyol.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.trendyol.R
import com.example.trendyol.databinding.FragmentMainBinding
import com.example.trendyol.ui.MainActivity
import com.example.trendyol.ui.MainViewModel
import com.example.trendyol.ui.pages.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHome.adapter = homeAdapter
        viewModel.widgetResponse.observe(viewLifecycleOwner, Observer {
            homeAdapter.setData(it.widgets)
        })

        viewModel.productList.observe(viewLifecycleOwner, Observer { pair ->
            homeAdapter.setProducts(pair.first, pair.second,
                onClick = { product ->
                    viewModel.selectedProduct = product
                    Navigation.findNavController(requireActivity(), R.id.fragment_container_main)
                        .navigate(R.id.action_mainFragment_to_detailFragment)
            })
        })

        if (viewModel.widgetResponse.value == null){
            viewModel.getWidgets()
        }
    }

    override fun onResume() {
        super.onResume()
        initToolbar()
    }

    private fun initToolbar() {
        with(activity as MainActivity) {
            setTitle("Trendyol")
            isCloseVisible(false)
            isBackVisible(false)
        }
    }
}