package com.juntadeandalucia.ced.newipasen.operations.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class LoginViewState : Parcelable {

    @Parcelize
    data class Login(
        var error: String = "",
        var loginOk: Boolean = true,
        var loading: Boolean = false
    ) : LoginViewState()


}
