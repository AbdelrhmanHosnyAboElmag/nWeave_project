package com.example.nweave_project.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.nweave_project.utils.NavigationCommand
import kotlinx.coroutines.Job

abstract class BaseFragment<VB : ViewBinding>(private val bindingInflater: (Inflater: LayoutInflater) -> VB) :
    Fragment() {
    private var job: Job? = null

    /**
     * Every fragment has to have an instance of a view model that extends from the BaseViewModel
     */
    abstract val _viewModel: BaseViewModel

    /**
     * Every fragment has to have an instance of a onclick that extends from the BaseViewModel
     */
    abstract fun onReigsterClick()

    /**
     * Every fragment has to have an instance of a onclick that extends from the BaseViewModel
     */
    abstract fun fragmentVisible()

    /**
     * Every fragment has to have an instance of a onclick that extends from the BaseViewModel
     */
    abstract fun fragmentFocus()


    /**
     * Every fragment has to have an instance of a binding that extends from the BaseViewModel
     */
    private var _binding: VB? = null
    val binding: VB get() = _binding as VB


    override fun onStart() {
        super.onStart()
        _viewModel.showErrorMessage.observe(this) {
            // Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
        _viewModel.showToast.observe(this) {
            // Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
        _viewModel.showSnackBar.observe(this) {
            // Snackbar.make(this.requireView(), it, Snackbar.LENGTH_LONG).show()
        }
        _viewModel.showSnackBarInt.observe(this) {
            // Snackbar.make(this.requireView(), getString(it), Snackbar.LENGTH_LONG).show()
        }


        /**
         * BaseFragment listens to navigation commands from a ViewModel
         */

        _viewModel.navigationCommand.observe(this) { command ->
            when (command) {
                is NavigationCommand.To -> findNavController().navigate(command.directions)
                is NavigationCommand.Back -> findNavController().popBackStack()
                is NavigationCommand.BackTo -> findNavController().popBackStack(
                    command.destinationId,
                    false
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        onReigsterClick()
        fragmentFocus()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentVisible()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null)
            throw java.lang.IllegalArgumentException("Binding cannot be null")
        return binding.root
    }



}