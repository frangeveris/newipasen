package com.juntadeandalucia.ced.newipasen.operations.Login

import com.juntadeandalucia.ced.domain.RepositoryFailure
import com.juntadeandalucia.ced.domain.operations.login.CheckLogin
import com.juntadeandalucia.ced.domain.operations.login.CheckLoginFailure
import com.juntadeandalucia.ced.domain.operations.login.LoginError
import com.juntadeandalucia.ced.domain.operations.login.LoginInput
import com.juntadeandalucia.ced.newipasen.base.BaseViewModel

class LoginViewModel(val checkLogin: CheckLogin) :
    BaseViewModel<LoginViewState, LoginViewTransition>() {

    private val state by lazy { viewState.value as? LoginViewState.Login ?: LoginViewState.Login() }


     fun checkLogin( username: String, password: String){

         state.loading = true
         viewState.value = state
        checkLogin(LoginInput(username,password,"{\"version\":\"" + "11.8.3.4" + "\"}")){
            state.loading = false
            viewState.value = state
            it.fold(::handleLoginError,
                    ::handleSucces)
        }
    }

    private fun handleLoginError(checkLoginError: CheckLoginFailure) {

        when (checkLoginError) {
            is CheckLoginFailure.Repository -> handleRepositoryError(checkLoginError.error)
            is CheckLoginFailure.Know -> when(checkLoginError.error){
                is LoginError.UserBloqued -> { viewTransition.value = LoginViewTransition.OnWelcome("USUARIO BLOQUEADO")}
                is LoginError.UserIncorrect -> viewTransition.value = LoginViewTransition.OnWelcome("USUAIRO INCORRECTO") }
            }



    }

    private fun handleRepositoryError(error: RepositoryFailure) {

        when (error) {
            is RepositoryFailure.NoInternet -> {viewTransition.value = LoginViewTransition.OnWelcome("NO INTERNET") }
            is RepositoryFailure.ServerError -> {viewTransition.value = LoginViewTransition.OnWelcome("SERVER ERROR")}
            is RepositoryFailure.Unauthorized -> {viewTransition.value = LoginViewTransition.OnWelcome("UNAUTORIZA") }
            is RepositoryFailure.Unknown ->  {viewTransition.value = LoginViewTransition.OnWelcome("ERROR DESCONOCIDO")}

        }
    }

    private fun handleSucces(unit: Unit) {
        viewTransition.value = LoginViewTransition.OnWelcome("Bienvenido")
    }


}