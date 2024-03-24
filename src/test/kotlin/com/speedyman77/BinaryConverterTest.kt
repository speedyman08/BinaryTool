package com.speedyman77

import com.speedyman77.exceptions.BinaryLengthException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class BinaryConverterTest {
    @Test
    fun binaryConversion() {
        val expected = 'a'
        val actual = BinaryConverter.binaryToAscii("01100001")
        assertEquals(expected, actual)
    }

    @Test
    fun invalidBinary() {
        val exception = assertThrows<BinaryLengthException> {
            BinaryConverter.binaryToAscii("01")
        }
        assertEquals("Binary number must be 8 bits long", exception.message)
    }
}