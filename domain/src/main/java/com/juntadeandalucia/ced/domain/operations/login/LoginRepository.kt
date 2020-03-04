package com.juntadeandalucia.ced.domain.operations.login

import com.juntadeandalucia.ced.commons.data.types.Either

interface LoginRepository {
    suspend fun checkLogin(loginInput: LoginInput): Either<CheckLoginFailure, Unit>
}