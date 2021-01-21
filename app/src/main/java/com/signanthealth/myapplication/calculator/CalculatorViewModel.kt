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
    DECIMAL;
}

enum class Operation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    MOD,
    EQUALS,
    CLEAR
}

class CalculatorViewModel : ViewModel() {

    private val announceOutputDisplay = MutableLiveData("")
    val outputDisplay: LiveData<String> = announceOutputDisplay

    fun userAction(buttonAction: ButtonAction) {
        if(buttonAction is ButtonAction.DigitAction) handleNumber(buttonAction.digit)
        else if(buttonAction is ButtonAction.OperationAction) handleOperation(buttonAction.operation)
    }

    private fun handleNumber(num: Digit) {

    }

    private fun handleOperation(op:Operation){

    }

}