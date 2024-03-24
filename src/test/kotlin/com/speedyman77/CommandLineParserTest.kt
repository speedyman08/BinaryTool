package com.speedyman77

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class CommandLineParserTest {
    private val parser = CommandLineParser(
        requiredArgs = arrayOf("data"),
        optionArgs = arrayOf("a","b")
    )
    @Test
    fun parseOnlyData() {
        val parsedArgs = parser.parseCommandLine(arrayOf("-data", "Random Data"))
        assertEquals(parsedArgs["data"], "Random Data")
    }
    @Test
    fun parseDataAndOption() {
        val parsedArgs = parser.parseCommandLine(arrayOf("-a", "-data", "Random Data"))
        assert(parsedArgs.containsKey("a"))
        assertEquals(parsedArgs["data"], "Random Data")
    }
    @Test
    fun parseWithoutRequired() {
        val exception = assertThrows<IllegalArgumentException> { parser.parseCommandLine(arrayOf("-dat", "Something")) }
        assertEquals("Missing required argument(s): data", exception.message)
    }
    @Test
    fun parseDataWithoutValue() {
        val exception = assertThrows<IllegalArgumentException> { parser.parseCommandLine(arrayOf("-data")) }
        assertEquals("No value provided for required arg data", exception.message)
    }
}