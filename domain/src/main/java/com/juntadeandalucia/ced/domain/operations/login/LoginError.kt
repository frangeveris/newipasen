package com.juntadeandalucia.ced.domain.operations.login

sealed class LoginError {
    object WRONG_USER_PASSWORD : LoginError()
    object BLOCKED_USER : LoginError()

}
