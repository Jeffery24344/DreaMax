package com.bigbigdreams.dreamax.install

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

sealed class InstallEvent {
    data class Success(val packageName: String?) : InstallEvent()
    data class Failure(val packageName: String?, val message: String) : InstallEvent()
}

object InstallEventBus {
    private val _events = MutableSharedFlow<InstallEvent>(extraBufferCapacity = 8)
    val events: SharedFlow<InstallEvent> = _events.asSharedFlow()

    fun emit(event: InstallEvent) {
        _events.tryEmit(event)
    }
}
