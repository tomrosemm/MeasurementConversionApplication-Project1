package com.example.measurementconversionapplication_project1

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.conversionSpinner)
        val unitOneTextView = findViewById<TextView>(R.id.unitOneText)
        val unitTwoTextView = findViewById<TextView>(R.id.unitTwoText)

        val unitOneNumber = findViewById<TextView>(R.id.unitOneNumber)
        val unitTwoNumber = findViewById<TextView>(R.id.unitTwoNumber)

        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.conversion_options, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {

                val input = unitOneNumber.text.toString().toDoubleOrNull() ?: 0.0
                var result = 0.0
                
                when (position) {
                    0 -> {
                        unitOneTextView.text = getString(R.string.miles)
                        unitTwoTextView.text = getString(R.string.kilometers)
                        result = input * 1.6094
                    }

                    1 -> {
                        unitOneTextView.text = getString(R.string.kilometers)
                        unitTwoTextView.text = getString(R.string.miles)
                        result = input / 1.6094
                    }

                    2 -> {
                        unitOneTextView.text = getString(R.string.inches)
                        unitTwoTextView.text = getString(R.string.centimeters)
                        result = input * 2.54
                    }

                    3 -> {
                        unitOneTextView.text = getString(R.string.centimeters)
                        unitTwoTextView.text = getString(R.string.inches)
                        result = input / 2.54
                    }

                    4 -> {
                        unitOneTextView.text = getString(R.string.teaspoons)
                        unitTwoTextView.text = getString(R.string.cups)
                        result = input / 48
                    }

                    5 -> {
                        unitOneTextView.text = getString(R.string.cups)
                        unitTwoTextView.text = getString(R.string.teaspoons)
                        result = input * 48
                    }

                    6 -> {
                        unitOneTextView.text = getString(R.string.ounces)
                        unitTwoTextView.text = getString(R.string.pounds)
                        result = input / 16
                    }

                    7 -> {
                        unitOneTextView.text = getString(R.string.pounds)
                        unitTwoTextView.text = getString(R.string.ounces)
                        result = input * 16
                    }
                }

                unitTwoNumber.text = String.format("%.4f", result)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }
}
