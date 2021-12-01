package de.stefan_oltmann

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class VersionNameGeneratorTest {

    @Test
    fun testGenerateVersionNameOnMonday() {

        Assertions.assertEquals(
            "21.48.0",
            VersionNameGenerator.generateVersionName(
                LocalDateTime.of(2021, 11, 29, 0, 0)
            )
        )
    }

    @Test
    fun testGenerateVersionNameOnMondayOneHourLater() {

        Assertions.assertEquals(
            "21.48.1",
            VersionNameGenerator.generateVersionName(
                LocalDateTime.of(2021, 11, 29, 1, 0)
            )
        )
    }

    @Test
    fun testGenerateVersionNameOnTuesday() {

        Assertions.assertEquals(
            "21.48.60",
            VersionNameGenerator.generateVersionName(
                LocalDateTime.of(2021, 12, 1, 12, 0)
            )
        )
    }

    @Test
    fun testGenerateVersionNameOnSunday() {

        Assertions.assertEquals(
            "21.48.167",
            VersionNameGenerator.generateVersionName(
                LocalDateTime.of(2021, 12, 5, 23, 59)
            )
        )
    }

    @Test
    fun testGenerateVersionNameForMinValue() {

        Assertions.assertEquals(
            "1.1.0",
            VersionNameGenerator.generateVersionName(
                LocalDateTime.of(2001, 1, 1, 0, 0)
            )
        )
    }

    @Test
    fun testGenerateVersionNameForMaxValue() {

        Assertions.assertEquals(
            "255.52.167",
            VersionNameGenerator.generateVersionName(
                LocalDateTime.of(2255, 12, 30, 23, 59)
            )
        )
    }
}
