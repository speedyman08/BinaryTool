package com.speedyman77

import com.speedyman77.exceptions.BinaryLengthException
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val parser = CommandLineParser(
        requiredArgs = arrayOf("data"),
        optionArgs = arrayOf("toBinary", "toAscii", "help")
    )
    val params: Map<String, String>
    try {
        params = parser.parseCommandLine(args)
    } catch(e: IllegalArgumentException) {
        println("\"data\" is a required argument, and it must have a value: ${e.message}")
        exitProcess(1)
    }

    // by default the program converts binary to ascii
    if ((!params.containsKey("toBinary") && !params.containsKey("toAscii")) || params.containsKey("toAscii")) {
        val userInput = params["data"]
        val builder = StringBuilder()

        userInput?.split(" ")?.forEach {
            try {
                builder.append(BinaryConverter.binaryToASCII(it))
            } catch (e: BinaryLengthException) {
                builder.append("*")
                // If the binary number's length is not 8 just replace it with an asterisk as it is considered invalid
            }
        }
        println(builder.toString())
    } else {
        // TODO: Implement Binary to ASCII logic
        println("Binary to ASCII not implemented yet")
    }

}