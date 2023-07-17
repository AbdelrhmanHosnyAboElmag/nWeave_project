package com.example.nweave_project.ui.fragment

import androidx.fragment.app.viewModels
import com.example.nweave_project.base.BaseFragment
import com.example.nweave_project.databinding.FragmentProductBinding
import com.example.nweave_project.mvvm.ProductViewModel

class ProductFragment: BaseFragment<FragmentProductBinding>(FragmentProductBinding::inflate) {
    override val _viewModel: ProductViewModel by viewModels()

    override fun onReigsterClick() {

    }

    override fun fragmentVisible() {

    }

    override fun fragmentFocus() {

    }


}