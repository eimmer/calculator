package com.signanthealth.myapplication.calculator

import com.signanthealth.myapplication.extensions.calculateTotal
import com.signanthealth.myapplication.extensions.formattedEquation
import com.signanthealth.myapplication.extensions.significantDigits
import com.signanthealth.myapplication.extensions.significantTotal
import com.signanthealth.myapplication.model.Equation
import com.signanthealth.myapplication.model.Operation
import junit.framework.TestCase

class EquationExtensionsKtTest : TestCase() {

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

    fun testSignificantTotal() {
        val equation = Equation("4", "2", Operation.ADD)
        assertEquals("6", equation.significantTotal())
    }

    fun testSignificantTotalForMultiply() {
        val equation = Equation(".2", ".2", Operation.MULTIPLY)
        assertEquals("0.04", equation.significantTotal())
    }

    fun testSignificantTotalForDivideBigDigits() {
        val equation = Equation(".3", ".3", Operation.DIVIDE)
        assertEquals("1", equation.significantTotal())
    }

    fun testDecimalRounding() {
        val equation = Equation("100", "3", Operation.DIVIDE)
        assertEquals("33.3333333", equation.significantTotal())
    }

    fun testFirstNumberOnly() {
        val equation = Equation(".3", "", Operation.ADD)
        assertEquals("0.3", equation.significantTotal())
    }

    fun testFormattedString() {
        val equation = Equation("4.12", "2.1", Operation.MULTIPLY)
        assertEquals("4.12 x 2.1", equation.formattedEquation())
    }

    fun testFormattedAdditionString() {
        val equation = Equation("2", "14", Operation.ADD)
        assertEquals("2 + 14", equation.formattedEquation())
    }

}