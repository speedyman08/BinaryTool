package com.speedyman77

fun main() {
    val userInput = readln() // TODO: change the user's input method to be a CLI argument
    val individualChars = userInput.split(" ") // [010101, 101010]
    val builder = StringBuilder()

    individualChars.forEach {
        try {
            builder.append(BinaryConverter.binaryToASCII(it))
        } catch(e: BinaryLengthException) {
            builder.append("*")
            // If the binary number's length is not 8 just replace it with an asterisk as it is unknown
        }
    }

    println(builder.toString())
}