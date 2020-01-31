package com.example.web

import org.jooq.DSLContext
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

class SampleConfig(
    val dataSource: DataSource
) {

    @Bean
    fun configuration(): org.jooq.Configuration {
        val config = DefaultConfiguration()
        config.set(DataSourceConnectionProvider(dataSource))
        config.set(org.jooq.SQLDialect.POSTGRES)
        config.settings().isRenderSchema = false
        config.settings().withRenderFormatted(false)
        config.settings().isExecuteLogging = false
        return config
    }

    @Bean
    fun dsl(configuration: org.jooq.Configuration): DSLContext {
        return DefaultDSLContext(configuration)
    }
}