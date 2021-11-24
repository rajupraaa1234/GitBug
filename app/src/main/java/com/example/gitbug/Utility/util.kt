package com.example.gitbug.Utility

object util {
    fun extractDate(str: String): String {
        if (str.isEmpty()) return ""
        val extractDate = str.substring(0, 10)
        val arr = extractDate.split("-").toTypedArray()
        return arr[1] + "-" + arr[2] + "-" + arr[0]
    }
}