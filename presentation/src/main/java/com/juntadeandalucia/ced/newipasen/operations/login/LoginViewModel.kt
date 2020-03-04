package com.juntadeandalucia.ced.newipasen.operations.login

import com.juntadeandalucia.ced.domain.RepositoryFailure
import com.juntadeandalucia.ced.domain.operations.login.CheckLogin
import com.juntadeandalucia.ced.domain.operations.login.CheckLoginFailure
import com.juntadeandalucia.ced.domain.operations.login.LoginError
import com.juntadeandalucia.ced.domain.operations.login.LoginInput
import com.juntadeandalucia.ced.newipasen.base.BaseViewModel

class LoginViewModel(val checkLogin: CheckLogin) :
    BaseViewModel<LoginViewState, LoginViewTransition>() {

    private val state by lazy { viewState.value as? LoginViewState.Login ?: LoginViewState.Login() }

    fun checkLogin(username: String, password: String) {
        state.loading = true
        viewState.value = state
        checkLogin(
            LoginInput(username, password, "{\"version\":\"" + "11.8.3.4" + "\"}")
        ) {
            state.loading = false
            viewState.value = state
            it.fold(::handleLoginError, ::handleLoginSuccess)
        }
    }

    private fun handleLoginSuccess(unit: Unit) {
        viewTransition.value = LoginViewTransition.OnWelcome("Bienvenido")
    }

    private fun handleLoginError(checkLoginFailure: CheckLoginFailure) {
        when (checkLoginFailure) {
            is CheckLoginFailure.Repository -> handleRepositoryError(checkLoginFailure.error)
            is CheckLoginFailure.Known -> when (checkLoginFailure.error) {
                is LoginError.BLOCKED_USER -> {
                    viewTransition.value = LoginViewTransition.OnWelcome("BLOCKED USER")
                }
                is LoginError.WRONG_USER_PASSWORD -> {
                    viewTransition.value = LoginViewTransition.OnWelcome("WRONG USER/PASSWORD")
                }


            }

        }
    }

    private fun handleRepositoryError(repositoryFailure: RepositoryFailure) {
        when (repositoryFailure) {
            is RepositoryFailure.NoInternet -> {
                viewTransition.value = LoginViewTransition.OnWelcome("NO INTERNET")
            }
            is RepositoryFailure.ServerError -> {
                viewTransition.value = LoginViewTransition.OnWelcome("SERVER ERROR")
            }
            is RepositoryFailure.Unauthorized -> {
                viewTransition.value = LoginViewTransition.OnWelcome("UNAUTHORIZED")
            }
            is RepositoryFailure.Unknown -> {
                viewTransition.value = LoginViewTransition.OnWelcome("UNKNOWN")
            }
        }
    }
}

