package com.example.web

import java.time.Duration
import java.time.LocalDate

data class Book(
    val title: String,
    val isbn: String,
    val publishDate: LocalDate
) {

    fun getDaysAgo(): Long {
        val from = publishDate.atStartOfDay()
        val to = LocalDate.now().atStartOfDay()
        return Duration.between(from, to).toDays()
    }
}