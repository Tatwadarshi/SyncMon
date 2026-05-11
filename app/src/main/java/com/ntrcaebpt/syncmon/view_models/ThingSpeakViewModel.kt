package com.ntrcaebpt.syncmon.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ntrcaebpt.syncmon.api_instances.ThingSpeakInstance
import com.ntrcaebpt.syncmon.api_services.ThingSpeakService
import com.ntrcaebpt.syncmon.datatypes.ThingSpeakResponse
import com.ntrcaebpt.syncmon.datatypes.ThingSpeakResponse2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class UiState {
    object Loading : UiState()
    data class Success(val data: ThingSpeakResponse) : UiState()
    data class Error(val message: String) : UiState()
}

class ThingSpeakViewModel(private val service: ThingSpeakService) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun fetchFeeds(channelId: String, apiKey: String, n: Int=20) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = service.getFeeds(channelId, apiKey, n)
                _uiState.value = UiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ThingSpeakViewModel(ThingSpeakInstance.api)
            }
        }
    }
}
