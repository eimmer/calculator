package com.signanthealth.myapplication.calculator

import kotlin.math.max

fun Equation.formattedEquation():String{
    return "$firstNumber ${operation?.display ?: ""} $secondNumber".trim()
}

fun Equation.calculateTotal(): Double {
    val n1 = firstNumber.toDouble()
    val n2 = secondNumber.toDoubleOrNull() ?: 0.0
    val function = operation

    if (function == Operation.ADD) return (n1 + n2)
    if (function == Operation.SUBTRACT) return (n1 - n2)
    if (function == Operation.DIVIDE) return (n1 / n2)
    if (function == Operation.MULTIPLY) return (n1 * n2)
    if (function == Operation.MOD) return (n1 % n2)
    return 0.0
}

/**
 * Returns the number of places after the decimal that should be allowed.
 * For math and subtraction, the number with the most decimal
 */
fun Equation.significantDigits(): Int {
    var numberOneDecimalCount = 0
    var numberTwoDecimalCount = 0

    val firstIndexOf = firstNumber.indexOf('.', 0)
    if (firstIndexOf > -1) {
        numberOneDecimalCount = firstNumber.length - firstIndexOf - 1
    } else {
        numberOneDecimalCount = 0
    }

    val secondIndexOf = secondNumber.indexOf('.', 0)
    if (secondIndexOf > -1) {
        numberTwoDecimalCount = secondNumber.length - secondIndexOf - 1
    } else {
        numberTwoDecimalCount = 0
    }

    if (operation == Operation.ADD || operation == Operation.SUBTRACT) return max(numberOneDecimalCount, numberTwoDecimalCount)
    if (operation == Operation.MULTIPLY || operation == Operation.DIVIDE || operation == Operation.MOD) return numberOneDecimalCount + numberTwoDecimalCount
    return 0
}

/**
 * Produces the total of the equation with the decimals rounded to the significant digit.
 */
fun Equation.significantTotal() : String {
    val format = "%.7f".format(calculateTotal())
    return format.trimEnd('0').trimEnd('.')
}

