package com.signanthealth.myapplication.model

data class Equation(
    val firstNumber:String,
    val secondNumber:String = "",
    val operation: Operation? = null)