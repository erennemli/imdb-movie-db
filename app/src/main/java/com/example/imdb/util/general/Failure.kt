package com.example.imdb.util.general

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.IOException

sealed class Failure : IOException(), Parcelable {
    @Parcelize
    class ApiError(var code: Int, override var message: String) : Failure()

    @Parcelize
    class UnknownError(val exception: Exception) : Failure()

    @Parcelize
    class TimeOutError(override var message: String?) : Failure()

    @Parcelize
    object NoConnectivityError : Failure()

    @Parcelize
    object EmptyResponse : Failure()

    @Parcelize
    object ReLoginError : Failure()
}
