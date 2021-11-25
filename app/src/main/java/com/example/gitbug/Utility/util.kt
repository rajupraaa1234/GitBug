package com.example.gitbug.Utility

import java.lang.StringBuilder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


object util {
    fun extractDate(str: String): String {
        if (str.isEmpty()) return ""
        val extractDate = str.substring(0, 10)
        val arr = extractDate.split("-").toTypedArray()
        return arr[1] + "-" + arr[2] + "-" + arr[0]
    }

    fun getDaysBetweenDates(start: String?, end: String?): Long {
        val DATE_FORMAT : String = "MM-dd-yyyy";
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)
        val startDate: Date
        val endDate: Date
        var numberOfDays: Long = 0
        try {
            startDate = dateFormat.parse(start)
            endDate = dateFormat.parse(end)
            numberOfDays = getUnitBetweenDates(startDate, endDate, TimeUnit.DAYS)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return numberOfDays
    }

    fun getUnitBetweenDates(startDate: Date, endDate: Date, unit: TimeUnit): Long {
        val timeDiff = endDate.time - startDate.time
        return unit.convert(timeDiff, TimeUnit.MILLISECONDS)
    }
    fun getTodayDate() : String{
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
        val formattedDate = df.format(c)
        return formattedDate;
    }

    fun getIntervalInWord(b: Long): String? {
        return if (b < 30) {
            val sb = StringBuilder()
            sb.append(b)
            sb.append(" day")
            sb.toString()
        } else if (b < 366) {
            val months = b / 30
            val days_out = b % 30
            val p1 = StringBuilder()
            val p2 = StringBuilder()
            val p3 = StringBuilder(" ")
            p1.append(days_out)
            p1.append(" day")
            p2.append(months)
            p2.append(" month")
            p2.append(p3)
            p2.append(p1)
            p2.toString()
        } else {
            val p1 = StringBuilder()
            val p2 = StringBuilder()
            val p3 = StringBuilder(" ")
            val p4 = StringBuilder()
            val years = b / 365
            val days_out = b % 365
            if (days_out > 30) {
                val m1 = days_out / 30
                val m2 = days_out % 30
                p2.append(years)
                p2.append(" year")
                p1.append(m1)
                p1.append(" month")
                p4.append(m2)
                p4.append(" day")
                p2.append(p3)
                p2.append(p1)
                p2.append(p3)
                p2.append(p4)
                p2.toString()
            } else {
                p4.append(days_out)
                p4.append(" day")
                p2.append(years)
                p2.append(" year")
                p2.append(p3)
                p2.append(p4)
                p2.toString()
            }
        }
    }
}