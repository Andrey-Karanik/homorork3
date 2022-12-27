package com.andreykaranik.homework3

import androidx.annotation.StringRes

interface HasCustomTitle {
    @StringRes
    fun getTitleRes(): Int
}