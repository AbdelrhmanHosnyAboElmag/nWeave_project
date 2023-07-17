package com.example.nweave_project.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner) {
        it.getValue()?.let(observer)
    }
}