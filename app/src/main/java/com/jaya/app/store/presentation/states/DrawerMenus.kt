package com.jaya.app.store.presentation.states

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jaya.app.store.R
import com.jaya.app.store.core.common.constants.Destination

enum class DrawerMenus(
    @StringRes val mName: Int,
    @DrawableRes val mIcon: Int,
    val mDestination: Destination
) {

    Logout(R.string.logout, R.drawable.jayalogo, Destination.NONE)

}