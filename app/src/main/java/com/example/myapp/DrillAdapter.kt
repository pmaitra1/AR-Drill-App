package com.example.myapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat

/**
 * Custom ArrayAdapter to style the Spinner with icons, disabled placeholder,
 * and clean dropdown formatting.
 */
class DrillAdapter(context: Context, private val items: List<String>) :
    ArrayAdapter<String>(context, R.layout.custom_spinner_item, items) {

    // Disable selection of the first item ("Select a drill")
    override fun isEnabled(position: Int): Boolean {
        return position != 0
    }

    // This view is shown as the selected item in the Spinner
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, parent, false)

        val textView = view.findViewById<TextView>(R.id.spinnerText)
        val icon = view.findViewById<AppCompatImageView>(R.id.spinnerIcon)

        // Set the label text
        textView.text = items[position]

        // Set color: gray for placeholder, black for others
        if (position == 0) {
            textView.setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray))
        } else {
            textView.setTextColor(ContextCompat.getColor(context, android.R.color.black))
        }

        // Show dropdown icon only for the first (placeholder) item
        icon.visibility = if (position == 0) View.VISIBLE else View.GONE

        return view
    }

    // This view is shown in the dropdown list when the spinner is tapped
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_dropdown_item, parent, false)

        val textView = view.findViewById<TextView>(R.id.spinnerText)
        textView.text = items[position]

        // Set text color based on whether it's the placeholder or a drill
        textView.setTextColor(
            if (position == 0)
                ContextCompat.getColor(context, android.R.color.darker_gray)
            else
                ContextCompat.getColor(context, android.R.color.black)
        )

        return view
    }
}
