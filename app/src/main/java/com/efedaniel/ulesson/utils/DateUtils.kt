package com.efedaniel.ulesson.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {

    const val ZULU_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    const val SHORT_DATE_TIME_FORMAT = "MMM d, h:mm a"
    const val TIME_FORMAT = "'Today', h:mm a"

    fun getRelativeDate(dateString: String): String {
        return try {
            val date = SimpleDateFormat(ZULU_FORMAT, Locale.getDefault()).parse(dateString)!!

            var receivedTime = Calendar.getInstance().also { it.timeInMillis = date.time }
            var currentTime = Calendar.getInstance()

            if (
                currentTime.get(Calendar.YEAR) == receivedTime.get(Calendar.YEAR) &&
                currentTime.get(Calendar.DAY_OF_YEAR) == receivedTime.get(Calendar.DAY_OF_YEAR)
            ) {
                formatDate(date, TIME_FORMAT)
            } else {
                formatDate(date, SHORT_DATE_TIME_FORMAT)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            dateString
        }
    }

    private fun formatDate(date: Date, format: String): String {
        val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
        return simpleDateFormat.format(date)
    }
}
