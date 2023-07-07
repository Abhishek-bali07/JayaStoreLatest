package com.jaya.app.store.core.common.constants

import android.appwidget.AppWidgetProvider
import android.util.Log

sealed class Destination(
 protected val route: String,
 vararg arguments: Any) {

 val fullRoute: String = if (arguments.isEmpty()) route else {
  val builder = StringBuilder(route)
  arguments.forEach { builder.append("/{${it}}") }
     Log.e("FullRoute", builder.toString())
  builder.toString()
 }

 sealed class NoArgumentsDestination(route: String) : Destination(route) {
  operator fun invoke(): String = route
 }

 object SplashScreen : Destination.NoArgumentsDestination(AppRoutes.SPLASH)


 object MobileNumberScreen: Destination.NoArgumentsDestination(AppRoutes.MOBILE)


 object DashBoardScreen: Destination.NoArgumentsDestination(AppRoutes.DASHBOARD)


 object OtpScreen : NoArgumentsDestination(AppRoutes.OTP)





}

private fun String.appendParams(vararg params: Pair<String, Any?>): String {
 val builder = StringBuilder(this)

 params.forEach {
  it.second?.toString()?.let { arg ->
   builder.append("/$arg")
  }
 }

 return builder.toString()
}