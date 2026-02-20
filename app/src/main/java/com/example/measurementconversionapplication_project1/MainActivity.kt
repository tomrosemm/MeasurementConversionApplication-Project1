package com.example.measurementconversionapplication_project1

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Get references to the views in the layout
        val spinner = findViewById<Spinner>(R.id.conversionSpinner)

        val unitOneTextView = findViewById<TextView>(R.id.unitOneText)
        val unitTwoTextView = findViewById<TextView>(R.id.unitTwoText)

        val unitOneNumber = findViewById<EditText>(R.id.unitOneNumber)
        val unitTwoNumber = findViewById<TextView>(R.id.unitTwoNumber)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.conversion_options,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        
        fun performConversion() {

            val input = unitOneNumber.text.toString().toDoubleOrNull()

            if (input == null) {
                unitTwoNumber.text = ""
                return
            }

            val result = when (spinner.selectedItemPosition) {

                0 -> { 
                    unitOneTextView.text = getString(R.string.miles)
                    unitTwoTextView.text = getString(R.string.kilometers)
                    input * 1.6094
                }

                1 -> { 
                    unitOneTextView.text = getString(R.string.kilometers)
                    unitTwoTextView.text = getString(R.string.miles)
                    input / 1.6094
                }

                2 -> { 
                    unitOneTextView.text = getString(R.string.inches)
                    unitTwoTextView.text = getString(R.string.centimeters)
                    input * 2.54
                }

                3 -> { 
                    unitOneTextView.text = getString(R.string.centimeters)
                    unitTwoTextView.text = getString(R.string.inches)
                    input / 2.54
                }

                4 -> { 
                    unitOneTextView.text = getString(R.string.teaspoons)
                    unitTwoTextView.text = getString(R.string.cups)
                    input / 48
                }

                5 -> { 
                    unitOneTextView.text = getString(R.string.cups)
                    unitTwoTextView.text = getString(R.string.teaspoons)
                    input * 48
                }

                6 -> { 
                    unitOneTextView.text = getString(R.string.ounces)
                    unitTwoTextView.text = getString(R.string.pounds)
                    input / 16
                }

                7 -> { 
                    unitOneTextView.text = getString(R.string.pounds)
                    unitTwoTextView.text = getString(R.string.ounces)
                    input * 16
                }

                else -> 0.0
            }

            unitTwoNumber.text = String.format("%.4f", result)
        }
        
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                performConversion()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        
        unitOneNumber.doAfterTextChanged {
            performConversion()
        }
    }
}
