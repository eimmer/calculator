package com.signanthealth.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        view.findViewById<Button>(R.id.equals).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}