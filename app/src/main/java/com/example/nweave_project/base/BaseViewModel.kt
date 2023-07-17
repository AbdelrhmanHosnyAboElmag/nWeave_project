package com.example.nweave_project.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.nweave_project.utils.NavigationCommand
import com.example.nweave_project.utils.SingleLiveEvent

/**
 * Base class for View Models to declare the common LiveData objects in one place
 */
abstract class BaseViewModel() : ViewModel() {


    val navigationCommand: SingleLiveEvent<NavigationCommand> = SingleLiveEvent()
    val showErrorMessage: SingleLiveEvent<String> = SingleLiveEvent()
    val showSnackBar: SingleLiveEvent<String> = SingleLiveEvent()
    val showSnackBarInt: SingleLiveEvent<Int> = SingleLiveEvent()
    val showToast: SingleLiveEvent<String> = SingleLiveEvent()
    val showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showNoData: MutableLiveData<Boolean> = MutableLiveData()
    fun navigateTo(directions: NavDirections) {
        navigationCommand.postValue(NavigationCommand.To(directions))
    }

    fun navigateBack() {
        navigationCommand.postValue(NavigationCommand.Back)
    }

    fun navigateBackTo(destinationId: Int) {
        navigationCommand.postValue(NavigationCommand.BackTo(destinationId))
    }

}