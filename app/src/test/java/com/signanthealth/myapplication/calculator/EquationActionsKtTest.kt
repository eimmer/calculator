package com.signanthealth.myapplication.calculator

import junit.framework.TestCase

class EquationActionsKtTest : TestCase() {

    fun testCalculateTotal() {
        val equation = Equation("4", "2", Operation.ADD)
        assertEquals(6.0, equation.calculateTotal())
    }

    fun testSignificantDigits() {
        val equation = Equation("4.1", "2", Operation.ADD)
        assertEquals(1, equation.significantDigits())
    }

    fun testTwoSignificantDigits() {
        val equation = Equation("4.12", "2", Operation.ADD)
        assertEquals(2, equation.significantDigits())
    }

    fun testSignificantDigitsForMultiplication() {
        val equation = Equation("4.1", "2", Operation.MULTIPLY)
        assertEquals(1, equation.significantDigits())
    }

    fun testTwoSignificantDigitsForMultiplication() {
        val equation = Equation("4.12", "2", Operation.MULTIPLY)
        assertEquals(2, equation.significantDigits())
    }

    fun testThreeSignificantDigitsForMultiplication() {
        val equation = Equation("4.12", "2.1", Operation.MULTIPLY)
        assertEquals(3, equation.significantDigits())
    }

    fun testSignificantTotal(){
        val equation = Equation("4", "2", Operation.ADD)
        assertEquals("6", equation.significantTotal())
    }

    fun testSignificantTotalForMultiply(){
        val equation = Equation(".2", ".2", Operation.MULTIPLY)
        assertEquals("0.04", equation.significantTotal())
    }

    fun testSignificantTotalForDivideBigDigits(){
        val equation = Equation(".3", ".3", Operation.DIVIDE)
        assertEquals("1", equation.significantTotal())
    }

    fun testDecimalRounding(){
        val equation = Equation("100", "3", Operation.DIVIDE)
        assertEquals("33", equation.significantTotal())
    }

}