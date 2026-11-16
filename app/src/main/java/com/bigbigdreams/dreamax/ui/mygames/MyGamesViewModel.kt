package com.bigbigdreams.dreamax.ui.mygames

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bigbigdreams.dreamax.data.repository.GameRepository
import com.bigbigdreams.dreamax.domain.model.Game
import com.bigbigdreams.dreamax.domain.model.GameFilter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class MyGamesUiState(
    val games: List<Game> = emptyList(),
    val isImporting: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)

class MyGamesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GameRepository(application)
    private val _isImporting = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)
    private val _success = MutableStateFlow<String?>(null)

    val uiState: StateFlow<MyGamesUiState> = combine(
        repository.observeGames(GameFilter.ALL),
        _isImporting,
        _error,
        _success
    ) { games, importing, error, success ->
        MyGamesUiState(
            games = games.sortedByDescending { it.lastUpdatedAt },
            isImporting = importing,
            errorMessage = error,
            successMessage = success
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MyGamesUiState()
    )

    fun clearMessages() {
        _error.value = null
        _success.value = null
    }

    fun importApk(uri: Uri) {
        viewModelScope.launch {
            _isImporting.value = true
            _error.value = null
            repository.importApk(uri).fold(
                onSuccess = { game -> _success.value = "Imported ${game.title}" },
                onFailure = { e -> _error.value = e.message ?: "Failed to import APK" }
            )
            _isImporting.value = false
        }
    }
}
