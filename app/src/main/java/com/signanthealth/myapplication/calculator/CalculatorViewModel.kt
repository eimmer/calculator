package com.signanthealth.myapplication.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

sealed class ButtonAction {
    class DigitAction(val digit: Digit) : ButtonAction()
    class OperationAction(val operation: Operation) : ButtonAction()
}

class CalculatorViewModel : ViewModel() {

    private val announceOutputDisplay = MutableLiveData("0")
    val outputDisplay: LiveData<String> = announceOutputDisplay

    private var currentEquation = Equation("0")

    fun userAction(buttonAction: ButtonAction) {
        if (buttonAction is ButtonAction.DigitAction) handleDigit(buttonAction.digit)
        else if (buttonAction is ButtonAction.OperationAction) handleOperation(buttonAction.operation)
    }

    private fun handleDigit(num: Digit) {
        if (currentEquation.operation == null) {
            currentEquation = if (currentEquation.firstNumber == "0") currentEquation.copy(firstNumber = num.value)
            else currentEquation.copy(firstNumber = currentEquation.firstNumber.plus(num.value))
        } else {
            currentEquation = if (currentEquation.secondNumber == "0") currentEquation.copy(secondNumber = num.value)
            else currentEquation.copy(secondNumber = currentEquation.secondNumber.plus(num.value))
        }
        updateDisplay()
    }

    private fun updateDisplay() {
        announceOutputDisplay.value = currentEquation.formattedEquation()
    }

    private fun handleOperation(userOperation: Operation) {
        when (userOperation) {
            Operation.CLEAR -> {
                currentEquation = Equation("0")
                updateDisplay()
                return
            }
            Operation.EQUALS -> {
                currentEquation = Equation(currentEquation.significantTotal())
                updateDisplay()
                return
            }
            Operation.DECIMAL -> {
                if (currentEquation.operation == null) {
                    if (!currentEquation.firstNumber.contains('.')) currentEquation =
                        Equation(firstNumber = currentEquation.firstNumber.plus('.'))
                } else {
                    if (!currentEquation.secondNumber.contains('.')) currentEquation =
                        currentEquation.copy(secondNumber = currentEquation.secondNumber.plus('.'))
                }
                return
            }
            else -> setOperation(userOperation)
        }
    }

    private fun setOperation(newOperation: Operation) {
        if (currentEquation.secondNumber.isNotEmpty()) {
            handleOperation(Operation.EQUALS)
        }
        currentEquation = currentEquation.copy(operation = newOperation)
        updateDisplay()
    }
}