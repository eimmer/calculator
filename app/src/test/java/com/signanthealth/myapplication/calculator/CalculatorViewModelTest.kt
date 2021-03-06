package com.signanthealth.myapplication.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.signanthealth.myapplication.model.Digit
import com.signanthealth.myapplication.model.Operation
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@Suppress("MemberVisibilityCanBePrivate")
@RunWith(AndroidJUnit4::class)
class CalculatorViewModelTest : TestCase() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<in String>

    lateinit var viewModel: CalculatorViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = CalculatorViewModel()
        viewModel.outputDisplay.observeForever(observer)
    }

    @Test
    fun testDefaultValue() {
        assertEquals("0", viewModel.outputDisplay.value)
    }

    @Test
    fun testDigitOne() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.ONE))
        assertEquals("1", viewModel.outputDisplay.value)
    }

    @Test
    fun testDigitOneAndOne() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.ONE))
        viewModel.userAction(ButtonAction.DigitAction(Digit.ONE))
        assertEquals("11", viewModel.outputDisplay.value)
    }

    @Test
    fun testDigitTwo() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.TWO))
        assertEquals("2", viewModel.outputDisplay.value)
    }

    @Test
    fun testDigitThree() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.THREE))
        assertEquals("3", viewModel.outputDisplay.value)
    }

    @Test
    fun testDigitFour() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.FOUR))
        assertEquals("4", viewModel.outputDisplay.value)
    }

    @Test
    fun testDigitFive() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.FIVE))
        assertEquals("5", viewModel.outputDisplay.value)
    }

    @Test
    fun testDigitSix() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.SIX))
        assertEquals("6", viewModel.outputDisplay.value)
    }

    @Test
    fun testDigitSeven() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.SEVEN))
        assertEquals("7", viewModel.outputDisplay.value)
    }

    @Test
    fun testDigitEight() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.EIGHT))
        assertEquals("8", viewModel.outputDisplay.value)
    }

    @Test
    fun testDigitNine() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
        assertEquals("9", viewModel.outputDisplay.value)
    }

    @Test
    fun testDigitZero() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.ZERO))
        assertEquals("0", viewModel.outputDisplay.value)
    }

    @Test
    fun testHundreds() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
        viewModel.userAction(ButtonAction.DigitAction(Digit.SIX))
        viewModel.userAction(ButtonAction.DigitAction(Digit.ZERO))
        assertEquals("960", viewModel.outputDisplay.value)
    }

    @Test
    fun testAdditionSignDisplay() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
        viewModel.userAction(ButtonAction.OperationAction(Operation.ADD))
        assertEquals("9 +", viewModel.outputDisplay.value)
    }

    @Test
    fun testFullAdditionEquationDisplay() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
        viewModel.userAction(ButtonAction.OperationAction(Operation.ADD))
        viewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
        assertEquals("9 + 9", viewModel.outputDisplay.value)
    }

    @Test
    fun testClearResetsDisplayTo0() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
        viewModel.userAction(ButtonAction.OperationAction(Operation.ADD))
        viewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
        viewModel.userAction(ButtonAction.OperationAction(Operation.CLEAR))
        assertEquals("0", viewModel.outputDisplay.value)
    }

    @Test
    fun testNumbersAfterClearDisplayCorrectly() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
        viewModel.userAction(ButtonAction.OperationAction(Operation.ADD))
        viewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
        viewModel.userAction(ButtonAction.OperationAction(Operation.CLEAR))
        assertEquals("0", viewModel.outputDisplay.value)
        viewModel.userAction(ButtonAction.DigitAction(Digit.SEVEN))
        assertEquals("7", viewModel.outputDisplay.value)
        viewModel.userAction(ButtonAction.OperationAction(Operation.ADD))
        viewModel.userAction(ButtonAction.DigitAction(Digit.EIGHT))
        viewModel.userAction(ButtonAction.OperationAction(Operation.EQUALS))
        assertEquals("15", viewModel.outputDisplay.value)
    }

    @Test
    fun testAdditionEqualityTotal() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
        viewModel.userAction(ButtonAction.OperationAction(Operation.ADD))
        viewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
        viewModel.userAction(ButtonAction.OperationAction(Operation.EQUALS))
        assertEquals("18", viewModel.outputDisplay.value)
    }

    @Test
    fun testMultiplicationEqualityTotal() {
        viewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
        viewModel.userAction(ButtonAction.OperationAction(Operation.MULTIPLY))
        viewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
        viewModel.userAction(ButtonAction.OperationAction(Operation.EQUALS))
        assertEquals("81", viewModel.outputDisplay.value)
    }

    @Test
    fun testDecimalAddsNumbersToCorrectSpot() {
        viewModel.userAction(ButtonAction.OperationAction(Operation.DECIMAL))
        viewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
        assertEquals("0.9", viewModel.outputDisplay.value)
    }
}