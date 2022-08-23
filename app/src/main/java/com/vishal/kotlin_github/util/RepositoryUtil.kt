package com.vishal.kotlin_github.util

import com.vishal.kotlin_github.R

class RepositoryUtil() {

    fun getUsername(fullName: String): String{
        var index: Int = fullName.indexOf('/')
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

    fun getLastUpdated(updatedAt: String): String {
        val date: String = updatedAt.substring(0, 11)
        return "Updated yesterday"
    }
}