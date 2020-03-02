package com.juntadeandalucia.ced.data.operations.login

import com.juntadeandalucia.ced.commons.data.types.Either
import com.juntadeandalucia.ced.data.BaseResponse
import com.juntadeandalucia.ced.data.remote.NoContentResponse
import com.juntadeandalucia.ced.data.remote.ParsedResponse
import com.juntadeandalucia.ced.data.remote.RemoteDataSourceExecutor
import com.juntadeandalucia.ced.domain.operations.login.CheckLoginFailure
import com.juntadeandalucia.ced.domain.operations.login.LoginError
import com.juntadeandalucia.ced.domain.operations.login.LoginInput
import com.juntadeandalucia.ced.domain.operations.login.LoginRepository

class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val remoteDataSourceExecutor: RemoteDataSourceExecutor
) : LoginRepository {


    override suspend fun checkLogin(loginInput: LoginInput): Either<CheckLoginFailure, Unit> {
        val loginRequest =
            LoginRequest(loginInput.username, loginInput.password, loginInput.version)

        val parsedResponse: ParsedResponse<LoginError, NoContentResponse> =
            remoteDataSourceExecutor { loginRemoteDataSource.checkLogin(loginRequest) }

        return when (parsedResponse) {
            is ParsedResponse.Success -> {
                Either.Right(Unit)
            }

            is ParsedResponse.KnownError -> Either.Left(CheckLoginFailure.Know(parsedResponse.knownError))

            is ParsedResponse.Failure -> Either.Left(CheckLoginFailure.Repository(parsedResponse.failure))
        }
    }


}