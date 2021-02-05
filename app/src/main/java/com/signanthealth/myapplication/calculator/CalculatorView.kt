package com.signanthealth.myapplication.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.signanthealth.myapplication.R
import com.signanthealth.myapplication.databinding.FragmentFirstBinding
import com.signanthealth.myapplication.model.Digit
import com.signanthealth.myapplication.model.Operation

class CalculatorView : Fragment(), View.OnClickListener {

    private lateinit var calculatorViewModel: CalculatorViewModel
    private lateinit var binding: FragmentFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calculatorViewModel = CalculatorViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calculatorViewModel.outputDisplay.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.display)?.text = it
        }

        binding.one.setOnClickListener(this)
        binding.two.setOnClickListener(this)
        binding.three.setOnClickListener(this)
        binding.four.setOnClickListener(this)
        binding.five.setOnClickListener(this)
        binding.six.setOnClickListener(this)
        binding.seven.setOnClickListener(this)
        binding.eight.setOnClickListener(this)
        binding.nine.setOnClickListener(this)
        binding.zero.setOnClickListener(this)
        binding.decimal.setOnClickListener(this)
        binding.add.setOnClickListener(this)
        binding.minus.setOnClickListener(this)
        binding.multiply.setOnClickListener(this)
        binding.devide.setOnClickListener(this)
        binding.mod.setOnClickListener(this)
        binding.equals.setOnClickListener(this)
        binding.clear.setOnClickListener(this)
    }

    override fun onClick(viewItem: View?) {
        when (viewItem?.id) {
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