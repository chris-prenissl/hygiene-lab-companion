package com.christophprenissl.hygienecompanion.domain.model.util.mapper

interface DataMapper<D, E> {

    fun fromEntity(entity: E): D

    fun toEntity(domain: D): E
}