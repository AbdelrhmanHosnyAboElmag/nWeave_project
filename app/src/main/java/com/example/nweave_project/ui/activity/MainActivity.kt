package com.example.nweave_project.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.example.nweave_project.R
import com.example.nweave_project.mvvm.ProductViewModel
import com.example.nweave_project.utils.DataResult
import com.example.nweave_project.utils.Status
import com.example.nweave_project.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mViewModelActivity by viewModels<ProductViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModelActivity.loadProductsFromApi()
    }

    override fun onResume() {
        super.onResume()
        mViewModelActivity.products.observeEvent(this) { result ->
            when (result.status) {
                Status.LOADING->{
                    Log.d("rrrrsaa", "onResume: 1")
                }
                Status.LOADING_MORE->{

                }
                Status.SUCCESS->{
                    Log.d("rrrrsaa", "onResume: 2 ${result.data?.get(0)}")
                    mViewModelActivity.loadProductsFromDataBase()
                }
                else ->{
                    Log.d("rrrrsaa", "onResume: 3 ${result.exception?.message}")

                }
            }
        }

        mViewModelActivity.productsDatabase.observeEvent(this) { result ->
            when (result.status) {
                Status.LOADING->{
                    Log.d("rrrrsaa", "onResume:database 1")
                }
                Status.LOADING_MORE->{

                }
                Status.SUCCESS->{
                    Log.d("rrrrsaa", "onResume: database ${result.data?.get(0)}")
                }
                else ->{
                    Log.d("rrrrsaa", "onResume: database ${result.exception?.message}")

                }
            }
        }
    }
}