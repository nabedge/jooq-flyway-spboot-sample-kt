//
val dbHost: String by extra
val dbPort: String by extra
val dbName: String by extra
val dbUser: String by extra
val dbPassword: String by extra
val jooqDestDir: String = "$buildDir/jooq-gen/"
val flywayVersion: String by extra

plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.flywaydb.flyway") version "6.2.0"
    kotlin("jvm")
    kotlin("plugin.spring")
}

buildscript {
    val flywayVersion: String by extra
    val postgreSqlDriverVersion: String by extra
    dependencies {
        classpath(group = "org.postgresql", name = "postgresql", version = postgreSqlDriverVersion)
        classpath(group = "org.flywaydb", name = "flyway-gradle-plugin", version = flywayVersion)
    }
}

flyway {
    url = "jdbc:postgresql://${dbHost}:${dbPort}/${dbName}"
    user = dbUser
    password = dbPassword
}

dependencies {
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-jooq")
    testImplementation(group = "org.flywaydb", name = "flyway-core", version = flywayVersion)
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<JavaCompile> {
    sourceSets["main"].java.srcDir(jooqDestDir)
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    enabled = false
}
