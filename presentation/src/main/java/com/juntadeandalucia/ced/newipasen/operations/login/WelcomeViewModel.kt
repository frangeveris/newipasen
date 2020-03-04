package com.juntadeandalucia.ced.newipasen.operations.login

import com.juntadeandalucia.ced.newipasen.base.BaseViewModel

class WelcomeViewModel : BaseViewModel<WelcomeViewState, WelcomeViewTransition>() {
    private val state by lazy {
        viewState.value as? WelcomeViewState.Welcome ?: WelcomeViewState.Welcome()
    }

    fun init(message: String) {
        state.welcomeMessage = message
        viewState.value = state
    }
}