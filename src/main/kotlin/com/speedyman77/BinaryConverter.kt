package com.speedyman77

import kotlin.math.pow

object BinaryConverter {
    private fun binaryToInt(binary: String): Int {
        if (binary.length != 8) {
            throw BinaryLengthException("Binary number must be 8 bits long")
        }

        var converted = 0.0

        binary.forEachIndexed { i, v ->
            if (v == '1') {
                converted += 2.0.pow((binary.length - i - 1).toDouble())
            }
        }

        return converted.toInt()
    }

    fun binaryToASCII(binary: String): Char {
        val binaryAsInt = binaryToInt(binary)
        return binaryAsInt.toChar()
    }
}

class BinaryLengthException(message: String) : Exception(message)

