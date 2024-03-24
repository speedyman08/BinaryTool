package com.speedyman77

import com.speedyman77.exceptions.BinaryLengthException

fun main(args: Array<String>) {
    val parser = CommandLineParser(arrayOf("path"))
    val params = parser.parseCommandLine(args)

    println("DEBUG: Application Arguments:")
    params.forEach { (key, value) ->
        println("$key: $value")
    }

    val userInput = readln() // TODO: change the user's input method to be a CLI argument
    val individualChars = userInput.split(" ") // [010101, 101010]
    val builder = StringBuilder()

    individualChars.forEach {
        try {
            builder.append(BinaryConverter.binaryToASCII(it))
        } catch(e: BinaryLengthException) {
            builder.append("*")
            // If the binary number's length is not 8 just replace it with an asterisk as it is invalid
        }
    }

    println(builder.toString())
}