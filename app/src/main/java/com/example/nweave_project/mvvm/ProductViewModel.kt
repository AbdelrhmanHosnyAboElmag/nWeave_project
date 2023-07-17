package com.example.nweave_project.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nweave_project.base.BaseViewModel
import com.example.nweave_project.model.Product
import com.example.nweave_project.model.ProductModelItem
import com.example.nweave_project.source.local.ProductDatabase
import com.example.nweave_project.usecase.GetProductDataBaseUseCase
import com.example.nweave_project.usecase.GetProductUseCase
import com.example.nweave_project.utils.DataResult
import com.example.nweave_project.utils.Event
import com.example.nweave_project.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val getProductUseCaseApi: GetProductUseCase,private val getProductUseCaseDataBase: GetProductDataBaseUseCase
): BaseViewModel() {

    private val _products = MutableLiveData<Event<DataResult<MutableList<ProductModelItem>>>>()
    val products: LiveData<Event<DataResult<MutableList<ProductModelItem>>>>
        get() = _products

    fun loadProductsFromApi() {
        // Avoid requesting more data while previous request is in progress
        val status = products.value?.getForcedValue()?.status
        if (status == Status.LOADING) return
        viewModelScope.launch {
            _products.value = Event(DataResult.loading())
            _products.value = Event(getProductUseCaseApi.getProductFromApi())
        }
    }

    private val _productsDatabase = MutableLiveData<Event<DataResult<MutableList<ProductDatabase>>>>()
    val productsDatabase: LiveData<Event<DataResult<MutableList<ProductDatabase>>>>
        get() = _productsDatabase

    fun loadProductsFromDataBase() {
        // Avoid requesting more data while previous request is in progress
        val status = productsDatabase.value?.getForcedValue()?.status
        if (status == Status.LOADING) return
        viewModelScope.launch {
            _productsDatabase.value = Event(DataResult.loading())
            _productsDatabase.value = Event(getProductUseCaseDataBase.getProductFromDatabase())
        }
    }
}