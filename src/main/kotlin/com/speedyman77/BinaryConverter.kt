package com.speedyman77

import com.speedyman77.exceptions.BinaryLengthException
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

    fun binaryToAscii(binary: String): Char {
        return binaryToInt(binary).toChar()
    }

    fun asciiToBinary(ascii: String): String {
        val binaryBuilder = StringBuilder()
        ascii.forEach {
            var binary = ""
            var code = it.code

            for (i in 7 downTo 0) {
                if (code - 2.0.pow(i) >= 0) {
                    binary += "1"
                    code -= 2.0.pow(i).toInt()
                } else {
                    binary += "0"
                    continue
                }
            }
            binaryBuilder.append("$binary ")
        }
        return binaryBuilder.toString()
    }
}



