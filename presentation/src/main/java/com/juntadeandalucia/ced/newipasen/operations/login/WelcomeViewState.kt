package com.juntadeandalucia.ced.newipasen.operations.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class WelcomeViewState : Parcelable {
    @Parcelize
    data class Welcome(var welcomeMessage: String? = null) : WelcomeViewState()
}


