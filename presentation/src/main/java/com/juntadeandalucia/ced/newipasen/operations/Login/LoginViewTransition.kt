package com.juntadeandalucia.ced.newipasen.operations.Login

sealed class LoginViewTransition {
    data class OnWelcome(val message: String): LoginViewTransition()
}