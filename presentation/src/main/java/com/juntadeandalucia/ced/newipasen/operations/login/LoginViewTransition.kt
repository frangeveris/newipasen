package com.juntadeandalucia.ced.newipasen.operations.login

sealed class LoginViewTransition {
    data class OnWelcome(val message: String) : LoginViewTransition()
}
