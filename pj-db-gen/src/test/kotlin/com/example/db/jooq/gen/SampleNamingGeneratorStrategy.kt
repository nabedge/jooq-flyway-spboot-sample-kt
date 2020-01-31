package com.example.db.jooq.gen

import org.jooq.codegen.DefaultGeneratorStrategy
import org.jooq.codegen.GeneratorStrategy
import org.jooq.meta.Definition

open class SampleNamingGeneratorStrategy: DefaultGeneratorStrategy() {

    override fun getJavaClassName(definition: Definition, mode: GeneratorStrategy.Mode): String {

        val name = super.getJavaClassName(definition, mode)

        return when (mode) {
            GeneratorStrategy.Mode.POJO -> "${name}Vo"
            GeneratorStrategy.Mode.RECORD -> name
            GeneratorStrategy.Mode.DEFAULT -> "J${name}"
            else -> "J${name}"
        }
    }
}