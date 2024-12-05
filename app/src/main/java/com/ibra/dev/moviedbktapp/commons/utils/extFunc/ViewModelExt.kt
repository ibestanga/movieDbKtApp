package com.ibra.dev.moviedbktapp.commons.utils.extFunc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.launchWithIO(block: suspend () -> Unit) {
    viewModelScope.launch(Dispatchers.IO) {
        block()
    }
}