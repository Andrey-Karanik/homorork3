package com.andreykaranik.homework3

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

typealias ResultListener<T> = (T) -> Unit

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {
    fun showEditTripFragment()
    fun goBack()
    fun goToMenu()
    fun <T: Parcelable> publishResult(result: T)
    fun <T: Parcelable> listenResult(clazz: Class<T>, owner: LifecycleOwner, listener: ResultListener<T>)
}