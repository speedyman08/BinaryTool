package com.speedyman77


/**
 * Custom implementation of a command line parser
 * @constructor Takes an array of accepted CLI arguments
 * @property specifiedOptions Array of strings that represent accepted arguments
 * @throws IllegalArgumentException if a __required__ argument does not have an associated value
 */
class CommandLineParser(requiredArgs: Array<String>, optionalArgs: Array<String>) {
    private var specifiedOptions: MutableList<String> = mutableListOf()
    private var optionalValues: MutableList<String> = mutableListOf()
    init {
        requiredArgs.forEach {
            specifiedOptions.add(it)
        }
        optionalArgs.forEach {
            optionalValues.add(it)
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

                if (specifiedOptions.contains(option) || optionalValues.contains(option)) {
                    if (index + 1 < args.size && !args[index + 1].startsWith("-")) {
                        optionsMap[option] = args[index + 1]
                        consumedNext = true
                    } else if (specifiedOptions.contains(option)) {
                        throw IllegalArgumentException("No value provided for required arg $option")
                    } else {
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