package com.example.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class SampleController(
    val bookRepository: BookRepository
) {

    @GetMapping("")
    fun index(model: Model): String {
        val books = bookRepository.selectAll()
        model.addAttribute("books", books)
        return "index"
    }
}