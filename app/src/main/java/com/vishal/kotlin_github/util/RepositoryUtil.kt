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

        val date = updatedAt.substring(0, 10)
        val current = LocalDateTime.now()
        return "Updated yesterday"
    }
}