package com.christophprenissl.hygienecompanion.presentation.util

import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingSeriesType
import com.christophprenissl.hygienecompanion.util.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.DATE

fun Date.dayMonthYearString(): String {
    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY)
    return formatter.format(this.time)
}

fun createDates(startDate: Date, endDate: Date, samplingSeriesTye: SamplingSeriesType): List<Date> {
    val dates = mutableListOf<Date>()
    when(samplingSeriesTye) {
        SamplingSeriesType.NonPeriodic -> dates.add(startDate)
        SamplingSeriesType.Daily -> {
            dates.addPeriodicDays(startDate, endDate, 1)
        }
        SamplingSeriesType.Weekly -> {
            dates.addPeriodicDays(startDate, endDate, WEEK_DAYS)
        }
        SamplingSeriesType.Monthly -> {
            dates.addPeriodicDays(startDate, endDate, MONTH_DAYS)
        }
        SamplingSeriesType.Quarterly -> {
            dates.addPeriodicDays(startDate, endDate, QUARTER_YEAR_DAYS)
        }
        SamplingSeriesType.HalfAYear -> {
            dates.addPeriodicDays(startDate, endDate, HALF_YEAR_DAYS)
        }
        SamplingSeriesType.Yearly -> {
            dates.addPeriodicDays(startDate, endDate, YEAR_DAYS)
        }
    }
    return dates
}

fun MutableList<Date>.addPeriodicDays(start: Date, end: Date, period: Int) {
    val c = Calendar.getInstance()
    val it = Date(start.time)
    while (it.time <= end.time) {
        add(Date(it.time))
        c.add(DATE, period)
        it.time = c.timeInMillis
    }
}
