package com.speedyman77


/**
 * Custom implementation of a command line parser
 * @constructor Takes an array of CLI arguments
 * @property specifiedOptions Array of strings that represent accepted arguments
 * @property optionValues Array of strings that represent arguments that do not need to have an associated value
 * @throws IllegalArgumentException if a __required__ argument does not have an associated value
 * @throws IllegalArgumentException if a required argument is not set
 */
class CommandLineParser(requiredArgs: Array<String>, optionArgs: Array<String>) {
    private var specifiedOptions: MutableList<String> = mutableListOf()
    private var optionValues: MutableList<String> = mutableListOf()
    init {
        requiredArgs.forEach {
            specifiedOptions.add(it)
        }
        optionArgs.forEach {
            optionValues.add(it)
        }
    }
    fun parseCommandLine(args: Array<String>): Map<String, String> {
        val optionsMap = mutableMapOf<String,String>()
        var consumedNext = false // if this is true it means that we should not process the arg since it is already consumed

        args.forEachIndexed { index, arg ->
            if (consumedNext) {
                consumedNext = false
                return@forEachIndexed
            }

            if (arg.startsWith("-")) {
                val option = arg.substring(1)

                if (specifiedOptions.contains(option) || optionValues.contains(option)) {
                    if (index + 1 < args.size && !args[index + 1].startsWith("-")) {
                        optionsMap[option] = args[index + 1]
                        consumedNext = true
                    } else if (specifiedOptions.contains(option)) {
                        // in the case that a value is required
                        throw IllegalArgumentException("No value provided for required arg $option")
                    } else {
                        // in the case that the argument is an option
                        optionsMap[option] = ""
                    }
                }
            }
        }

        val missingOptions = specifiedOptions.filter { option -> !optionsMap.containsKey(option) }
        if (missingOptions.isNotEmpty()) {
            throw IllegalArgumentException("Missing required argument(s): ${missingOptions.joinToString(", ")}")
        }

        return optionsMap
    }

}