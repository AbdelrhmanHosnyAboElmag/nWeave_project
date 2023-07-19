package com.example.nweave_project.ui.fragment

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.nweave_project.R
import com.example.nweave_project.base.BaseFragment
import com.example.nweave_project.databinding.FragmentProductDetailBinding
import com.example.nweave_project.mvvm.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment: BaseFragment<FragmentProductDetailBinding>(FragmentProductDetailBinding::inflate) {
    override val _viewModel: ProductViewModel by activityViewModels()
    private val args: ProductDetailFragmentArgs by navArgs()
    private var isFirstTime = false

    override fun onReigsterClick() {

    }

    override fun fragmentVisible() {
        if (!isFirstTime){
            setupView()
            isFirstTime = true
        }
    }

    override fun fragmentFocus() {

    }

    private fun setupView(){
        binding.tvTitleDetail.text = _viewModel.productsDatabase.value?.getForcedValue()?.data?.get(args.position)?.name.toString()
        Glide.with(requireContext())
            .load(_viewModel.productsDatabase.value?.getForcedValue()?.data?.get(args.position)?.image_url)
            .centerCrop()
            .placeholder(R.drawable.no_picture_found)
            .into(binding.imgItem)
    }




}