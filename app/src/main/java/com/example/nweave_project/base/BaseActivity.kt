package com.example.nweave_project.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>(private val bindingInflater: (Inflater: LayoutInflater) -> VB) :
    AppCompatActivity() {
    private var _binding: VB? = null
    val binding: VB get() = _binding as VB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        if (_binding == null)
            throw java.lang.IllegalArgumentException("Binding cannot be null")
        setContentView(binding.root)
    }


}