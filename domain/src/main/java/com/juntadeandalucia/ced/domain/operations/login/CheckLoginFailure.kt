package com.juntadeandalucia.ced.domain.operations.login

import com.juntadeandalucia.ced.domain.RepositoryFailure

sealed class CheckLoginFailure {
    data class Repository(val error: RepositoryFailure) : CheckLoginFailure()

    data class Known(val error: LoginError) : CheckLoginFailure()

}