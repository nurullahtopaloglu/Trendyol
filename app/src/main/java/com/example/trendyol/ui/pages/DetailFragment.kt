package com.example.trendyol.ui.pages

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.trendyol.R
import com.example.trendyol.databinding.FragmentDetailBinding
import com.example.trendyol.model.ProductResponse
import com.example.trendyol.ui.MainActivity
import com.example.trendyol.ui.MainViewModel
import com.example.trendyol.ui.pages.adapter.ProductCarouselAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var vpAdapter: ProductCarouselAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedProduct = viewModel.selectedProduct
        selectedProduct?.let {
            setVpAdapter(it)
            setUI(it)
        }
    }

    private fun setVpAdapter(model: ProductResponse) {
        binding.vpImages.adapter = vpAdapter
        vpAdapter.setData(model)

        binding.txtVpState.text = "1/" + model.imageUrls.size
        binding.vpImages.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.txtVpState.text = (position + 1).toString() + "/" + model.imageUrls.size
            }
        })
    }

    private fun setUI(productResponse: ProductResponse) {
        binding.txtTitle.text = buildSpannedString {
            inSpans(binding.txtTitle.typeface, StyleSpan(Typeface.BOLD), ForegroundColorSpan(ContextCompat.getColor(binding.txtTitle.context, R.color.black))) { append(productResponse.brandName) }
            append(" ")
            inSpans(binding.txtTitle.typeface, StyleSpan(Typeface.NORMAL), ForegroundColorSpan(ContextCompat.getColor(binding.txtTitle.context, R.color.black_80))) { append(productResponse.name) }
        }

        binding.txtPrice.text = productResponse.salePrice.toString() + " TL"
    }

    override fun onResume() {
        super.onResume()
        initToolbar()
    }

    private fun initToolbar() {
        with(activity as MainActivity) {
            setTitle("Detail")
            isCloseVisible(false)
            isBackVisible(true)
        }
    }
}