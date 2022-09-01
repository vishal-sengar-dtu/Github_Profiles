package com.vishal.kotlin_github.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.vishal.kotlin_github.R
import java.time.LocalDate
import java.time.LocalDateTime

object RepositoryUtil {

    fun getUsername(fullName: String): String{
        val index: Int = fullName.indexOf('/')
        return fullName.substring(0, index)
    }

    fun getDescription(description: String?): String? {
        return if (description != null && description.length > 120) description.substring(0, 120) + " ..." else description
    }

    fun getImage(): Int {
        val arrImg = intArrayOf(
            R.drawable.js,
            R.drawable.google,
            R.drawable.apple,
            R.drawable.figma,
            R.drawable.app_store,
            R.drawable.illustrator,
            R.drawable.slack,
            R.drawable.wordpress,
            R.drawable.python
        )
        val index = (9 * Math.random()).toInt()
        return arrImg[index]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getLastUpdated(updatedAt: String): String {
        val ch: String
        val currentDateTime = LocalDateTime.now().toString()

        val YY = currentDateTime.substring(0, 4).toInt() - updatedAt.substring(0, 4).toInt()
        val MM = currentDateTime.substring(5, 7).toInt() - updatedAt.substring(5, 7).toInt()
        val DD = currentDateTime.substring(8, 10).toInt() - updatedAt.substring(8, 10).toInt()
        val HH = currentDateTime.substring(11, 13).toInt() - updatedAt.substring(11, 13).toInt()
        val mm = currentDateTime.substring(14, 16).toInt() - updatedAt.substring(14, 16).toInt()
        val SS = currentDateTime.substring(17, 19).toInt() - updatedAt.substring(17, 19).toInt()

        ch = if(YY > 0) "year"
        else if(MM > 0) "month"
        else if(DD > 0) "day"
        else if(HH > 0) "hour"
        else if(mm > 0) "minute"
        else "second"

        val lastUpdatedAt = when(ch){
            "year" -> "Updated $YY $ch ago"
            "month" -> "Updated $MM $ch ago"
            "day" -> "Updated $DD $ch ago"
            "hour" -> "Updated $HH $ch ago"
            "minute" -> "Updated $mm $ch ago"
            else -> "Updated $SS $ch ago"
        }

        return lastUpdatedAt
    }

}