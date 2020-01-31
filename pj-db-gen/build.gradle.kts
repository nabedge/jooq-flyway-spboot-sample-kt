//
val dbHost: String by extra
val dbPort: String by extra
val dbName: String by extra
val dbUser: String by extra
val dbPassword: String by extra
val jooqDestDir: String = "$buildDir/jooq-gen/"
val jooqDestPackage: String = "gen"
val jooqVersion: String by extra
val flywayVersion: String by extra
val postgreSqlDriverVersion: String by extra

plugins {
    kotlin("jvm")
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation(group = "org.jooq", name = "jooq-codegen", version = jooqVersion)
    testImplementation(group = "org.postgresql", name = "postgresql", version = postgreSqlDriverVersion)
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter", version="5.5.2")
}
