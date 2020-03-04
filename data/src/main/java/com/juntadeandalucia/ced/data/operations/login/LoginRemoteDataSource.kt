package com.juntadeandalucia.ced.data.operations.login

import com.juntadeandalucia.ced.data.remote.NoContentResponse
import com.juntadeandalucia.ced.data.remote.ParsedResponse
import com.juntadeandalucia.ced.data.remote.ResponseParse
import com.juntadeandalucia.ced.data.remote.ResponseParse.Companion.REQUEST_OP_USER_BLOQUED
import com.juntadeandalucia.ced.data.remote.ResponseParse.Companion.REQUEST_OP_USER_ERROR
import com.juntadeandalucia.ced.domain.operations.login.LoginError
import okhttp3.ResponseBody
import retrofit2.Response
import kotlin.reflect.KClass

class LoginRemoteDataSource(private val service: LoginRetrofit, private val parser: ResponseParse) {
    suspend fun checkLogin(request: LoginRequest): ParsedResponse<LoginError, NoContentResponse> {
        val response: Response<ResponseBody> = service.checkLogin(
            request.username, request.password, request.version
        ).await()
        return parser.parseNoContentResponse(response, getKnownErrorClassByErrorCodes())
    }

    private fun getKnownErrorClassByErrorCodes(): Map<String, KClass<out LoginError>> = mapOf(
        REQUEST_OP_USER_ERROR to LoginError.WRONG_USER_PASSWORD::class,
        REQUEST_OP_USER_BLOQUED to LoginError.BLOCKED_USER::class
    )
}