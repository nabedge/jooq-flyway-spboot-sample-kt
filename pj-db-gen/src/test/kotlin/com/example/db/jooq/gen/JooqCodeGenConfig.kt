package com.example.db.jooq.gen

import org.jooq.codegen.GenerationTool
import org.jooq.meta.jaxb.*
import org.jooq.meta.jaxb.Target
import org.junit.jupiter.api.Test

class JooqCodeGenConfig {

    @Test
    fun generate() {

        val sourceDirectory = "../pj-db/build/jooq-gen"
        val packageName = "com.example.db.jooq.gen"
        val strategyName = SampleNamingGeneratorStrategy::class.java.name
        val postgresDriverName = org.postgresql.Driver::class.java.name
        val generatorMetaName = org.jooq.meta.postgres.PostgresDatabase::class.java.name

        val jdbcUrl = "jdbc:postgresql://127.0.0.1:5432/sampledb"
        val dbUser = "sampledbuser"
        val dbPassword = "samplepassword"
        val dbSchema = "public"

        val config = Configuration()
            .withJdbc(Jdbc()
                .withDriver(postgresDriverName)
                .withUrl(jdbcUrl)
                .withUser(dbUser)
                .withPassword(dbPassword))
            .withGenerator(Generator()
                .withDatabase(Database()
                    .withName(generatorMetaName)
                    .withIncludes(".*")
                    .withExcludes("flyway_schema_history")
                    .withInputSchema(dbSchema)
                )
                .withGenerate(Generate()
                    .withImmutablePojos(true)
                    .withPojosEqualsAndHashCode(true)
                    .withSerializablePojos(true))
                .withStrategy(Strategy()
                    .withName(strategyName))
                .withTarget(Target()
                    .withPackageName(packageName)
                    .withDirectory(sourceDirectory)))

        GenerationTool.generate(config)
    }
}