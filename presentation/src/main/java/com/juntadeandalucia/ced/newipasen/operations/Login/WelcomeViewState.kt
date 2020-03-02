package com.juntadeandalucia.ced.newipasen.operations.Login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class WelcomeViewState : Parcelable {

    @Parcelize
    data class Welcome(var welcomwMessage: String? = null) : WelcomeViewState()
}