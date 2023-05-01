package com.example.turno

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.turno.R.*


class MilesToKmActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_miles_to_km)


        fun convertDistance(distance: Double, unit: String): Double {
            return when (unit.lowercase()) {
                "miles" -> distance * 1.60934
                "kilometers", "kilometres" -> distance * 0.621371
                else -> throw IllegalArgumentException("Invalid unit: $unit")
            }
        }

        val distanceInput = findViewById<EditText>(id.distanceInput)
        val unitSelection = findViewById<RadioGroup>(id.unitSelection)
        val convertButton = findViewById<Button>(id.convertButton)
        val resultOutput = findViewById<TextView>(id.resultOutput)


        convertButton.setOnClickListener {
            val distance = distanceInput.text.toString().toDoubleOrNull()
            if (distance == null) {
                resultOutput.text = getString(string.invalid) // invalid Input. Enter valid number
                return@setOnClickListener
            }
            val unit = if (unitSelection.checkedRadioButtonId == id.milesButton) {
                "miles"
            } else {
                "kilometers"
            }
            val convertedDistance = convertDistance(distance, unit)
            if (convertedDistance.isNaN()) {
                resultOutput.text =
                    getString(string.Invalid_unit) // Invalid unit. Enter miles or km
                return@setOnClickListener
            }
            resultOutput.text =
                "$distance $unit is $convertedDistance ${if (unit == "miles") "kilometers" else "miles"}"
        }

    }
}

