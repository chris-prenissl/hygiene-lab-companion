package com.christophprenissl.hygienecompanion.presentation.util

import com.christophprenissl.hygienecompanion.model.entity.SamplingSeriesType

import org.junit.Test
import java.util.*
import java.util.Calendar.*

class DateUtilKtTest {

    @Test
    fun createDates_withDayIntervals_expected4Days() {
        val c = getInstance()
        c.set(2022, 4,16)

        val startDate = Date(c.timeInMillis)

        c.set(2022,4,18)

        val endDate = Date(c.timeInMillis)

        val daily = SamplingSeriesType.Daily

        val result = createDates(
            startDate = startDate,
            endDate = endDate,
            samplingSeriesTye = daily
        )

        val expected = mutableListOf<Date>()
        c.set(2022, 4, 16)
        c.set(HOUR_OF_DAY, 0)
        c.set(MINUTE, 0)
        c.set(SECOND, 0)
        c.set(MILLISECOND, 0)
        val date16 = Date(c.timeInMillis)
        expected.add(date16)
        c.set(2022,4,17)
        c.set(HOUR_OF_DAY, 0)
        c.set(MINUTE, 0)
        c.set(SECOND, 0)
        c.set(MILLISECOND, 0)
        val date17 = Date(c.timeInMillis)
        expected.add(date17)
        c.set(2022, 4, 18)
        c.set(HOUR_OF_DAY, 0)
        c.set(MINUTE, 0)
        c.set(SECOND, 0)
        c.set(MILLISECOND, 0)
        val date18 = Date(c.timeInMillis)
        expected.add(date18)

        assert(result.containsAll(expected))
    }
}
