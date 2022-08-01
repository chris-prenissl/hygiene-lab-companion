package com.christophprenissl.hygienecompanion.model.util.mapper

import com.christophprenissl.hygienecompanion.model.entity.CoveringLetter
import org.junit.Assert.*

import org.junit.Test

class CoveringLetterMapperTest {

    @Test
    fun toEntity() {

        val mapper = CoveringLetterMapper()
        val coveringLetter = CoveringLetter()
        val entity = mapper.toEntity(coveringLetter)
    }
}