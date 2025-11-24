package com.example.space_marines.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.space_marines.data.Chapter
import com.example.space_marines.repository.ChaptersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface ChaptersUiState {
    object Loading : ChaptersUiState
    data class Success(val chapters: List<Chapter>) : ChaptersUiState
    data class Error(val message: String) : ChaptersUiState
}

class ChaptersViewModel(private val repo: ChaptersRepository) : ViewModel() {
    private val _state = MutableStateFlow<ChaptersUiState>(ChaptersUiState.Loading)
    val state: StateFlow<ChaptersUiState> = _state

    init { refresh() }

    fun refresh() {
        viewModelScope.launch {
            _state.value = ChaptersUiState.Loading
            try {
                _state.value = ChaptersUiState.Success(repo.loadChapters())
            } catch (e: Exception) {
                _state.value = ChaptersUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}