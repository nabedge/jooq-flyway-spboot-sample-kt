package testdata

import com.example.db.jooq.gen.tables.JBook
import com.example.db.jooq.gen.tables.records.BookRecord
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.springframework.jdbc.datasource.SingleConnectionDataSource
import java.sql.Date
import java.time.LocalDate
import javax.sql.DataSource

class R__ImportMoreBookWithJooq: BaseJavaMigration() {

    override fun migrate(context: Context) {
        val ds: DataSource = SingleConnectionDataSource(context.connection, true)

        val dslContext = DSL.using(context.connection, SQLDialect.POSTGRES)

        val baseDate = LocalDate.of(2010, 1, 1)

        for (i in 1..10) {
            val jBook: JBook = JBook.BOOK
            val rec: BookRecord = dslContext.newRecord(jBook)
            rec.isbn = "isbn-test-with-jooq-$i"
            rec.title = "test title with jooq $i"
            rec.publishDate = Date.valueOf(baseDate.plusDays(i.toLong()))
            rec.store()
        }
    }
}