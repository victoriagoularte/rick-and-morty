package com.viclab.core.viewmodel

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface UIAction

class Action<Action : UIAction> {

    private val _uiAction = MutableSharedFlow<Action>()
    val uiAction: SharedFlow<Action> = _uiAction.asSharedFlow()

    suspend fun emitEffect(effect: () -> Action) {
        _uiAction.emit(effect())
    }
}