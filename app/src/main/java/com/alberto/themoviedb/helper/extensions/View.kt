package com.alberto.themoviedb.helper.extensions

import android.view.View

internal fun View.visibleIf(show: Boolean) {
    if (show) show()
    else gone()
}

internal fun View.show() {
    visibility = View.VISIBLE
}

internal fun View.gone() {
    visibility = View.GONE
}