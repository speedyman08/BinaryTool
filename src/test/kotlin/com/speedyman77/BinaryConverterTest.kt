package com.speedyman77

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BinaryConverterTest {
    @Test
    fun binaryToASCII() {
        val expected = 'a'
        val actual = BinaryConverter.binaryToASCII("01100001")
        assertEquals(expected, actual)
    }
}