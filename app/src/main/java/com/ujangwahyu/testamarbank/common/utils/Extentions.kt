package com.ujangwahyu.testamarbank.common.utils

import android.app.DatePickerDialog
import android.view.View
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun Fragment.showDatePicker(dateValue:(String) -> Unit) {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val datePickerDialog = DatePickerDialog(this.requireContext(),
        { _, years, monthOfYear, dayOfMonth ->
            val date = (years.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString())
            dateValue.invoke(date.dateFormatter())
        },
        year, month, day
    )
    datePickerDialog.datePicker.maxDate = Date().time
    datePickerDialog.show()
}

fun String.dateFormatter(
    sourcePattern: String = "yyyy-MM-dd",
    targetPattern: String = "dd-MM-yyyy",
    locale: Locale = Locale("in", "ID"),
): String {
    val date = SimpleDateFormat(sourcePattern, locale).parse(this)!!
    return SimpleDateFormat(targetPattern, Locale.getDefault()).format(date)
}
