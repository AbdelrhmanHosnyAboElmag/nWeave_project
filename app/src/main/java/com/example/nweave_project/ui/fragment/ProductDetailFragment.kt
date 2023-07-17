package com.example.nweave_project.ui.fragment

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.nweave_project.base.BaseFragment
import com.example.nweave_project.databinding.FragmentProductDetailBinding
import com.example.nweave_project.mvvm.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment: BaseFragment<FragmentProductDetailBinding>(FragmentProductDetailBinding::inflate) {
    override val _viewModel: ProductViewModel by activityViewModels()
    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onReigsterClick() {

    }

    override fun fragmentVisible() {
        Toast.makeText(requireContext(), args.position.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun fragmentFocus() {

    }


}