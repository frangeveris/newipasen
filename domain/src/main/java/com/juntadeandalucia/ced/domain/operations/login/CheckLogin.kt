package com.juntadeandalucia.ced.domain.operations.login

import com.juntadeandalucia.ced.commons.data.types.Either
import com.juntadeandalucia.ced.domain.base.BaseUseCase

class CheckLogin(private val repository: LoginRepository) :
    BaseUseCase<CheckLoginFailure, Unit, LoginInput>() {

    override suspend fun run(params: LoginInput): Either<CheckLoginFailure, Unit> {
        return repository.checkLogin(params)
    }
}