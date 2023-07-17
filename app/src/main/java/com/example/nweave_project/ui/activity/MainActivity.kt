package com.example.nweave_project.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.ui.navigateUp
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.nweave_project.R
import com.example.nweave_project.base.BaseActivity
import com.example.nweave_project.databinding.ActivityMainBinding
import com.example.nweave_project.mvvm.ProductViewModel
import com.example.nweave_project.utils.Status
import com.example.nweave_project.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavLister()
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * Setup activity navigation controller
     */
    private fun setupNavLister() {
        findNavController(R.id.myNavHostFragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}