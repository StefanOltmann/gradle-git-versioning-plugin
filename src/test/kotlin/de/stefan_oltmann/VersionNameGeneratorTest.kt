package de.stefan_oltmann

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class VersionNameGeneratorTest {

    @Test
    fun testGenerateVersionNameOnMonday() {

        Assertions.assertEquals(
            "21.37.4320",
            VersionNameGenerator.generateVersionName(
                LocalDateTime.of(2021, 9, 13, 12, 0)
            )
        )
    }

    @Test
    fun testGenerateVersionNameOnTuesday() {

        Assertions.assertEquals(
            "21.37.12960",
            VersionNameGenerator.generateVersionName(
                LocalDateTime.of(2021, 9, 14, 12, 0)
            )
        )
    }

    @Test
    fun testGenerateVersionNameOnSunday() {

        Assertions.assertEquals(
            "21.37.60474",
            VersionNameGenerator.generateVersionName(
                LocalDateTime.of(2021, 9, 19, 23, 59)
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
            "255.52.60474",
            VersionNameGenerator.generateVersionName(
                LocalDateTime.of(2255, 12, 30, 23, 59)
            )
        )
    }
}
