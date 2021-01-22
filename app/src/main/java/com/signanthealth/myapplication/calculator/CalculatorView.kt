package com.signanthealth.myapplication.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.signanthealth.myapplication.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CalculatorView : Fragment(), View.OnClickListener {

    lateinit var calculatorViewModel:CalculatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calculatorViewModel = CalculatorViewModel()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calculatorViewModel.outputDisplay.observe(viewLifecycleOwner){
            view.findViewById<TextView>(R.id.display)?.text = it
        }

        view.findViewById<Button>(R.id.one).setOnClickListener(this)
        view.findViewById<Button>(R.id.two).setOnClickListener(this)
        view.findViewById<Button>(R.id.three).setOnClickListener(this)
        view.findViewById<Button>(R.id.four).setOnClickListener(this)
        view.findViewById<Button>(R.id.five).setOnClickListener(this)
        view.findViewById<Button>(R.id.six).setOnClickListener(this)
        view.findViewById<Button>(R.id.seven).setOnClickListener(this)
        view.findViewById<Button>(R.id.eight).setOnClickListener(this)
        view.findViewById<Button>(R.id.nine).setOnClickListener(this)
        view.findViewById<Button>(R.id.zero).setOnClickListener(this)
        view.findViewById<Button>(R.id.decimal).setOnClickListener(this)
        view.findViewById<Button>(R.id.add).setOnClickListener(this)
        view.findViewById<Button>(R.id.minus).setOnClickListener(this)
        view.findViewById<Button>(R.id.multiply).setOnClickListener(this)
        view.findViewById<Button>(R.id.devide).setOnClickListener(this)
        view.findViewById<Button>(R.id.mod).setOnClickListener(this)
        view.findViewById<FloatingActionButton>(R.id.equals).setOnClickListener(this)
        view.findViewById<Button>(R.id.clear).setOnClickListener(this)
    }

    override fun onClick(viewItem: View?) {
        when(viewItem?.id){
            R.id.one -> calculatorViewModel.userAction(ButtonAction.DigitAction(Digit.ONE))
            R.id.two -> calculatorViewModel.userAction(ButtonAction.DigitAction(Digit.TWO))
            R.id.three -> calculatorViewModel.userAction(ButtonAction.DigitAction(Digit.THREE))
            R.id.four -> calculatorViewModel.userAction(ButtonAction.DigitAction(Digit.FOUR))
            R.id.five -> calculatorViewModel.userAction(ButtonAction.DigitAction(Digit.FIVE))
            R.id.six -> calculatorViewModel.userAction(ButtonAction.DigitAction(Digit.SIX))
            R.id.seven -> calculatorViewModel.userAction(ButtonAction.DigitAction(Digit.SEVEN))
            R.id.eight -> calculatorViewModel.userAction(ButtonAction.DigitAction(Digit.EIGHT))
            R.id.nine -> calculatorViewModel.userAction(ButtonAction.DigitAction(Digit.NINE))
            R.id.zero -> calculatorViewModel.userAction(ButtonAction.DigitAction(Digit.ZERO))

            R.id.decimal -> calculatorViewModel.userAction(ButtonAction.OperationAction(Operation.DECIMAL))
            R.id.add -> calculatorViewModel.userAction(ButtonAction.OperationAction(Operation.ADD))
            R.id.minus -> calculatorViewModel.userAction(ButtonAction.OperationAction(Operation.SUBTRACT))
            R.id.multiply -> calculatorViewModel.userAction(ButtonAction.OperationAction(Operation.MULTIPLY))
            R.id.devide -> calculatorViewModel.userAction(ButtonAction.OperationAction(Operation.DIVIDE))
            R.id.mod -> calculatorViewModel.userAction(ButtonAction.OperationAction(Operation.MOD))
            R.id.equals -> calculatorViewModel.userAction(ButtonAction.OperationAction(Operation.EQUALS))
            R.id.clear -> calculatorViewModel.userAction(ButtonAction.OperationAction(Operation.CLEAR))
        }
    }
}