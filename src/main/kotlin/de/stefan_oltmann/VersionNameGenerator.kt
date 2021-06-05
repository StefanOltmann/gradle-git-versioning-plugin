package de.stefan_oltmann

import java.time.LocalDateTime

object VersionNameGenerator {

    fun generateVersionName(dateTime: LocalDateTime) =
        "${generateFirst(dateTime)}.${generateSecond(dateTime)}.${generateThird(dateTime)}"

    private fun generateFirst(dateTime: LocalDateTime) =
        dateTime.year - 2000

    private fun generateSecond(dateTime: LocalDateTime) =
        dateTime.dayOfYear

    private fun generateThird(dateTime: LocalDateTime) =
        (dateTime.toLocalTime().toSecondOfDay()/60/1.5).toInt()

}