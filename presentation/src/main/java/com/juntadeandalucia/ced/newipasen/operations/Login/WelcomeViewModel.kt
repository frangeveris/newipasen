package com.juntadeandalucia.ced.newipasen.operations.Login

import com.juntadeandalucia.ced.newipasen.base.BaseViewModel

class WelcomeViewModel : BaseViewModel<WelcomeViewState, WelcomeViewTransition>() {

    private val state by lazy {
        viewState.value as? WelcomeViewState.Welcome ?: WelcomeViewState.Welcome()
    }


    fun init(message: String) {

        state.welcomwMessage = message
        viewState.value = state
    }
}