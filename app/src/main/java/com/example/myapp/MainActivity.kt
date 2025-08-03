package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // List of drills including a default placeholder option
    private val drills = listOf("Select a drill", "Drill 1", "Drill 2", "Drill 3")

    // Description for each drill
    private val drillDescriptions = mapOf(
        "Drill 1" to "High-speed drill for wood.",
        "Drill 2" to "Medium-speed drill for metal.",
        "Drill 3" to "Precision drill for electronics."
    )

    // Usage/safety tips for each drill
    private val drillTips = mapOf(
        "Drill 1" to "Use on softwood only.",
        "Drill 2" to "Wear safety goggles.",
        "Drill 3" to "Avoid overheating the tip."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reference all necessary UI components from the layout
        val spinner = findViewById<Spinner>(R.id.drillSpinner)
        val description = findViewById<TextView>(R.id.drillDescription)
        val tips = findViewById<TextView>(R.id.drillTips)
        val imageView = findViewById<ImageView>(R.id.drillImage)
        val startButton = findViewById<Button>(R.id.startARButton)

        // Hide all drill-specific UI elements initially (before selection)
        description.visibility = View.GONE
        tips.visibility = View.GONE
        imageView.visibility = View.GONE
        startButton.visibility = View.GONE

        // Set custom spinner adapter to display dropdown list
        val adapter = DrillAdapter(this, drills)
        spinner.adapter = adapter

        // Handle spinner item selection
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                // If placeholder option is selected, hide everything
                if (position == 0) {
                    description.visibility = View.GONE
                    tips.visibility = View.GONE
                    imageView.visibility = View.GONE
                    startButton.visibility = View.GONE
                    return
                }

                // If a drill is selected, fetch its info
                val selected = drills[position]
                description.text = "Description: ${drillDescriptions[selected]}"
                tips.text = "Tips: ${drillTips[selected]}"

                // Set the appropriate image for the selected drill
                val imageRes = when (selected) {
                    "Drill 1" -> R.drawable.drill1
                    "Drill 2" -> R.drawable.drill2
                    "Drill 3" -> R.drawable.drill3
                    else -> R.drawable.drill_placeholder
                }
                imageView.setImageResource(imageRes)

                // Show all drill-specific UI elements
                description.visibility = View.VISIBLE
                tips.visibility = View.VISIBLE
                imageView.visibility = View.VISIBLE
                startButton.visibility = View.VISIBLE
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing if nothing is selected
            }
        }

        // Launch AR drill activity with selected drill as an extra
        startButton.setOnClickListener {
            val intent = Intent(this, ARActivity::class.java)
            intent.putExtra("selectedDrill", spinner.selectedItem.toString())
            startActivity(intent)
        }
    }
}
