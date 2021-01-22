package com.signanthealth.myapplication.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

sealed class ButtonAction {
    class DigitAction(val digit: Digit) : ButtonAction()
    class OperationAction(val operation: Operation) : ButtonAction()
}

enum class Digit {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    ZERO,
}

enum class Operation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    MOD,
    EQUALS,
    CLEAR,
    DECIMAL
}

class CalculatorViewModel : ViewModel() {

    private val announceOutputDisplay = MutableLiveData("0.0")
    val outputDisplay: LiveData<String> = announceOutputDisplay

    var currentDigit = 0.0
    var decimalPlace = 0.1
    var isDecimal = false

    private var firstNumber = 0.0
    private var operation: Operation? = null
    private var secondNumber: Double? = null
    private var isCurrentNumberFirst = true


    fun userAction(buttonAction: ButtonAction) {
        if (buttonAction is ButtonAction.DigitAction) handleDigit(buttonAction.digit)
        else if (buttonAction is ButtonAction.OperationAction) handleOperation(buttonAction.operation)
    }

    private fun handleDigit(num: Digit) {
        addDigit(convertDigit(num))
    }

    private fun convertDigit(num: Digit): String {
        return when (num) {
            Digit.ONE -> "1"
            Digit.TWO -> "2"
            Digit.THREE -> "3"
            Digit.FOUR -> "4"
            Digit.FIVE -> "5"
            Digit.SIX -> "6"
            Digit.SEVEN -> "7"
            Digit.EIGHT -> "8"
            Digit.NINE -> "9"
            Digit.ZERO -> "0"
        }
    }

    private fun convertOperation(operation: Operation): String {
        return when (operation) {
            Operation.DECIMAL -> "."
            Operation.ADD -> " + "
            Operation.SUBTRACT -> " - "
            Operation.MULTIPLY -> " * "
            Operation.DIVIDE -> " / "
            Operation.MOD -> " % "
            Operation.EQUALS -> " = "
            Operation.CLEAR -> " C "
        }
    }

    private fun calculateTotal() : Double{
        val n1 = firstNumber
        val n2 = secondNumber!!
        val function = operation

        if(secondNumber == null || operation == null) {
            displayError()
            return 0.0
        }

        if(function == Operation.ADD) return (n1 + n2)
        if(function == Operation.SUBTRACT) return (n1 - n2)
        if(function == Operation.DIVIDE) return (n1 / n2)
        if(function == Operation.MULTIPLY) return (n1 * n2)
        if(function == Operation.MOD) return (n1 % n2)
        return 0.0
    }

    private fun displayError() {
        announceOutputDisplay.value = "ERROR"
    }

    private fun addDigit(digit: String) {
        if(isDecimal){
            currentDigit += (digit.toInt() * decimalPlace)
            decimalPlace *= 0.1
        } else {
            currentDigit = currentDigit * 10 + digit.toInt()
        }

        if (isCurrentNumberFirst) firstNumber = currentDigit
        else secondNumber = currentDigit
        updateDisplay()
    }

    private fun updateDisplay() {
        announceOutputDisplay.value =
            "$firstNumber${operation?.let { convertOperation(it) } ?: ""}${secondNumber?.toString() ?: ""}"
    }

    private fun handleOperation(userOperation: Operation) {
        when (userOperation) {
            Operation.CLEAR -> {
                currentDigit = 0.0
                firstNumber = 0.0
                secondNumber = null
                operation = null
                isCurrentNumberFirst = true
                isDecimal = false
                decimalPlace = 0.1
                updateDisplay()
            }
            Operation.EQUALS -> {
                firstNumber = calculateTotal()
                secondNumber = null
                operation = null
                isDecimal = false
                decimalPlace = 0.1
                updateDisplay()
            }
            Operation.DECIMAL -> {
                isDecimal = true
            }
            else -> setOperation(userOperation)
        }
    }

    private fun setOperation(newOperation: Operation) {
        if(secondNumber != null){
            handleOperation(Operation.EQUALS)
        }

        isCurrentNumberFirst = false
        isDecimal = false
        decimalPlace = 0.1
        operation = newOperation
        secondNumber = null
        currentDigit = 0.0
        updateDisplay()
    }
}