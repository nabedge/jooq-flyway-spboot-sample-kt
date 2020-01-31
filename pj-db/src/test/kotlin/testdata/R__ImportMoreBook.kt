package testdata

import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.SingleConnectionDataSource
import java.time.LocalDate
import javax.sql.DataSource

class R__ImportMoreBook: BaseJavaMigration() {

    override fun migrate(context: Context) {

        val ds: DataSource = SingleConnectionDataSource(context.connection, true) as DataSource
        val tmpl = JdbcTemplate(ds)

        val baseDate = LocalDate.of(1990, 1, 1)

        for (i in 1..100) {
            val isbn = "test-isbn-$i"
            val title = "test book $i"
            val publishDate = baseDate.plusDays(i.toLong())
            val sb = StringBuilder()
            sb.append("insert into book (isbn, title, publish_date) values (")
            sb.append("'$isbn',")
            sb.append("'$title',")
            sb.append("'$publishDate'")
            sb.append(")")
            tmpl.execute(sb.toString())
        }

    }
}