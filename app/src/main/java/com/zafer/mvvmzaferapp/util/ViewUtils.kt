package com.zafer.mvvmzaferapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.CalendarView
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun Context.toast(message: String) {

    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}

fun ProgressBar.show() {
    visibility = View.VISIBLE

}

fun ProgressBar.hide() {
    visibility = View.GONE

}

fun View.snackbar(message: String) {

    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()

}

@SuppressLint("SimpleDateFormat")
fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("yyyy.MM.dd")
    return format.format(date)
}

fun currentTimeToLong(): Long {
    return System.currentTimeMillis()
}

fun convertDateToLong(date: String): Long {
    val df = SimpleDateFormat("yyyy.MM.dd")
    return df.parse(date).time
}