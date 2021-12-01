package de.stefan_oltmann

import java.time.*
import java.time.temporal.WeekFields

object VersionNameGenerator {

    fun generateVersionName(commitTime: Int): String {

        val dateTime = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(commitTime.toLong()),
            ZoneId.systemDefault()
        )

        return generateVersionName(dateTime)
    }

    fun generateVersionName(dateTime: LocalDateTime) =
        "${generateMajor(dateTime)}.${generateMinor(dateTime)}.${generatePatch(dateTime)}"

    private fun generateMajor(dateTime: LocalDateTime) =
        dateTime.year - 2000

    private fun generateMinor(dateTime: LocalDateTime) =
        dateTime.get(WeekFields.ISO.weekOfWeekBasedYear())

    private fun generatePatch(dateTime: LocalDateTime): Long {

        val startOfWeek = dateTime
            .with(WeekFields.ISO.dayOfWeek(), DayOfWeek.MONDAY.value.toLong())
            .withHour(0)
            .withMinute(0)
            .withSecond(0)

        return Duration.between(startOfWeek, dateTime).toHours()
    }
}
