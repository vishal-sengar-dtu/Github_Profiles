package com.vishal.kotlin_github.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.vishal.kotlin_github.R
import java.time.LocalDateTime

object RepositoryUtil {

    fun getUsername(fullName: String): String{
        val index: Int = fullName.indexOf('/')
        return fullName.substring(0, index)
    }

    fun getDescription(description: String?): String? {
        return if(description == null) ""
        else if (description.length > 120) description.substring(0, 120) + " ..." else description
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

        val yY = currentDateTime.substring(0, 4).toInt() - updatedAt.substring(0, 4).toInt()
        val mM = currentDateTime.substring(5, 7).toInt() - updatedAt.substring(5, 7).toInt()
        val dD = currentDateTime.substring(8, 10).toInt() - updatedAt.substring(8, 10).toInt()
        val hH = currentDateTime.substring(11, 13).toInt() - updatedAt.substring(11, 13).toInt()
        val mm = currentDateTime.substring(14, 16).toInt() - updatedAt.substring(14, 16).toInt()
        val sS = currentDateTime.substring(17, 19).toInt() - updatedAt.substring(17, 19).toInt()

        ch = if(yY > 0) "year"
        else if(mM > 0) "month"
        else if(dD > 0) "day"
        else if(hH > 0) "hour"
        else if(mm > 0) "minute"
        else "second"

        val lastUpdatedAt = when(ch){
            "year" -> "Updated $yY $ch ago"
            "month" -> "Updated $mM $ch ago"
            "day" -> "Updated $dD $ch ago"
            "hour" -> "Updated $hH $ch ago"
            "minute" -> "Updated $mm $ch ago"
            else -> "Updated $sS $ch ago"
        }

        return lastUpdatedAt
    }

}