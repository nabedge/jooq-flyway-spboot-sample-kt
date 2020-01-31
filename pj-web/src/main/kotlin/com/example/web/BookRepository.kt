package com.example.web

import com.example.db.jooq.gen.tables.JBook
import com.example.db.jooq.gen.tables.pojos.BookVo
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class BookRepository(
    val dslContext: DSLContext
) {

    val jBook: JBook = JBook.BOOK

    fun selectAll(): List<Book> {
        val selected: List<BookVo> = dslContext
            .select(
                jBook.ISBN,
                jBook.TITLE,
                jBook.PUBLISH_DATE
            )
            .from(jBook)
            .orderBy(jBook.PUBLISH_DATE)
            .fetchInto(BookVo::class.java)

        return selected
            .map { bookVo: BookVo ->
                Book(
                    bookVo.title,
                    bookVo.isbn,
                    bookVo.publishDate.toLocalDate()
                )
            }
            .toList()
    }
}
