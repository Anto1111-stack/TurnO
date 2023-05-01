package com.example.turno

// Owner Antonio Vrsalovic. Created: 01.05.2023
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.*
import com.example.turno.R.*


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)


        val buttonMiles: Button = findViewById(id.btn_miles)

        buttonMiles.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, MilesToKmActivity::class.java)

            startActivity(intent)
        }

    }

        fun fahrenheitToCelsius(fahrenheit: Int): Int {
            return (fahrenheit - 32) * 5 / 9

        }

        fun celsiusToFahrenheit(celsius: Int): Int {
            return celsius * 9 / 5 + 32
        }

    fun onConvertButtonClick(view: View) {
        val temperatureEditText = findViewById<EditText>(R.id.temperatureEditText)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val unitRadioGroup = findViewById<RadioGroup>(R.id.unitRadioGroup)

        val temperature = temperatureEditText.text.toString().toIntOrNull()
        val unit = when (unitRadioGroup.checkedRadioButtonId) {
            R.id.fahrenheitRadioButton -> "F"
            R.id.celsiusRadioButton -> "C"
            else -> null
        }

        if (temperature == null || unit == null) {
            resultTextView.text = "Invalid input"
            return
        }

        val convertedTemperature = if (unit == "F") {
            val celsius = fahrenheitToCelsius(temperature)
            "$temperature°F = $celsius°C"
        } else {
            val fahrenheit = celsiusToFahrenheit(temperature)
            "$temperature°C = $fahrenheit°F"
        }

        resultTextView.text = convertedTemperature
    }
}