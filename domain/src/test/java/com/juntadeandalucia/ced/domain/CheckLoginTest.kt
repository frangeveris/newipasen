package com.juntadeandalucia.ced.domain

import com.juntadeandalucia.ced.commons.data.types.Either
import com.juntadeandalucia.ced.commons.test.mock
import com.juntadeandalucia.ced.domain.operations.login.CheckLoginFailure
import com.juntadeandalucia.ced.domain.operations.login.LoginError
import com.juntadeandalucia.ced.domain.operations.login.LoginInput
import com.juntadeandalucia.ced.domain.operations.login.LoginRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.`when`


class CheckLoginTest {
    val repository: LoginRepository = mock()

    @Test
    fun `Login With Success`() = runBlocking {
        //GIVEN
        val loginResult: Either<CheckLoginFailure, Unit> = mock()
        val loginParams = LoginInput("ipasen11", "ipasen11", "{\"version\":\"" + "11.8.3.4" + "\"}")
        `when`(
            repository.checkLogin(loginParams)
        ).thenReturn(loginResult)


        //WHEN
        val result: Either<CheckLoginFailure, Unit> = repository.checkLogin(loginParams)
        //THEN

        assert(result == loginResult)
    }

    @Test
    fun `Login With WRONG USER_PASSWORD`() = runBlocking {
        //GIVEN
        val wrongUserPasswordResult: Either<CheckLoginFailure, Unit> =
            Either.Left(CheckLoginFailure.Known(LoginError.WRONG_USER_PASSWORD))

        val loginParams = LoginInput("ipasen10", "ipasen10", "{\"version\":\"" + "11.8.3.4" + "\"}")

        `when`(
            repository.checkLogin(loginParams)
        ).thenReturn(wrongUserPasswordResult)


        //WHEN
        val result: Either<CheckLoginFailure, Unit> = repository.checkLogin(loginParams)
        //THEN
        System.out.println("ipasen: login WUP: " + result)
        assert(
            result.toLeftValueOrNull()
                    ==
                    wrongUserPasswordResult.toLeftValueOrNull()
        )
    }
}
