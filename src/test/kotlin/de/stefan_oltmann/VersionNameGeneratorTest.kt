package de.stefan_oltmann

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class VersionNameGeneratorTest {

    @Test
    fun testGenerateVersionName() {

        Assertions.assertEquals(
            "21.152.400",
            VersionNameGenerator.generateVersionName(
                LocalDateTime.of(2021, 6, 1, 10, 0)
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
            "999.365.959",
            VersionNameGenerator.generateVersionName(
                LocalDateTime.of(2999, 12, 31, 23, 59)
            )
        )
    }
}