plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

val postgreSqlDriverVersion: String by extra

dependencies {
    implementation(project(":pj-db"))
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation(group = "org.postgresql", name = "postgresql", version = postgreSqlDriverVersion)

    implementation(kotlin("stdlib"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.21")
}
