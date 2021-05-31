package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var collBack: Interface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        collBack = context as Interface?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        val minNumb = view.findViewById<EditText>(R.id.min_value)
        val maxNumb = view.findViewById<EditText>(R.id.max_value)

        generateButton?.setOnClickListener {

            val minN = minNumb.text.toString().toInt()
            val maxN = maxNumb.text.toString().toInt()

            if (minN < 0 || maxN < minN || maxN < 0 || minN == maxN) {
                val incorrect = Toast.makeText(activity, " incorrect data", Toast.LENGTH_SHORT)
                incorrect.show()

            } else {
                collBack?.onClick(minN, maxN)

            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }

    interface Interface {
        fun onClick(min: Int, max: Int)
        fun onClickBack(resultNumber: Int)
    }
}