package com.example.nweave_project.ui.fragment

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.nweave_project.adapter.ProductAdapter
import com.example.nweave_project.base.BaseFragment
import com.example.nweave_project.databinding.FragmentProductBinding
import com.example.nweave_project.mvvm.ProductViewModel
import com.example.nweave_project.source.local.ProductDatabase
import com.example.nweave_project.utils.Status
import com.example.nweave_project.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment: BaseFragment<FragmentProductBinding>(FragmentProductBinding::inflate) {
    override val _viewModel: ProductViewModel by activityViewModels()
    private lateinit var productAdapter: ProductAdapter

    override fun onReigsterClick() {

    }

    override fun fragmentVisible() {
        callApi()
        observeViewModel()
    }

    override fun fragmentFocus() {

    }
    private fun callApi(){
        _viewModel.loadProductsFromApi()
    }
    private fun observeViewModel(){
        _viewModel.products.observeEvent(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.LOADING->{
                    Log.d("rrrrsaa", "onResume: 1")
                }
                Status.LOADING_MORE->{

                }
                Status.SUCCESS->{
                    _viewModel.loadProductsFromDataBase()
                }
                else ->{
                    Toast.makeText(requireContext(), "Error occurred:${result.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        _viewModel.productsDatabase.observeEvent(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.LOADING->{
                }
                Status.LOADING_MORE->{

                }
                Status.SUCCESS->{
                    setupAdapter(result.data)
                }
                else ->{
                    Toast.makeText(requireContext(), "Error occurred:${result.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupAdapter(data: MutableList<ProductDatabase>?) {
        productAdapter = ProductAdapter(
            data as ArrayList<ProductDatabase>
        ){
            _viewModel.navigateTo(ProductFragmentDirections.actionProductFragmentToProductDetailFragment(it))

        }
        binding.rvProduct.adapter = productAdapter
    }


}