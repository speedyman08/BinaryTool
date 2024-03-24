package com.speedyman77


/**
 * Custom implementation of a command line parser
 * @constructor Takes an array of accepted CLI arguments
 * @property specifiedOptions Array of strings that represent accepted arguments
 * @throws IllegalArgumentException if a __required__ argument does not have an associated value
 */
class CommandLineParser(requiredOptions: Array<String>) {
    private var specifiedOptions: MutableList<String> = mutableListOf()

    init {
        requiredOptions.forEach {
            specifiedOptions.add(it)
        }
    }
    fun parseCommandLine(args: Array<String>): Map<String, String> {
        val optionsMap = mutableMapOf<String,String>()
        args.forEachIndexed { argIndex, arg ->
            if (arg.startsWith("-")) {
                val option = arg.substring(1)

                if (!specifiedOptions.contains(option)) {
                    // if the option is not needed
                    return@forEachIndexed // end current loop iteration
                }

                val value = if (argIndex + 1 < args.size && !args[argIndex + 1].startsWith("-")) {
                    // Check if there is a value after the option, and it is not another option
                    args[argIndex + 1]
                } else {
                    throw IllegalArgumentException("No value provided for option $option")
                }
                optionsMap[option] = value
            }
        }
        // throw an exception if the parameter "args" does not contain every required argument
        val missingOptions = specifiedOptions.filter { option -> !optionsMap.containsKey(option) }
        if (missingOptions.isNotEmpty()) {
            throw IllegalArgumentException("Missing required argument(s): ${missingOptions.joinToString(", ")}")
        }
        return optionsMap.toMap()
    }

}