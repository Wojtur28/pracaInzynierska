buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("io.swagger.codegen.v3:swagger-codegen-maven-plugin:3.0.16")
    }
}

plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.flywaydb.flyway") version "9.22.1"
    id("org.hidetake.swagger.generator") version "2.19.2"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.projectlombok:lombok:1.18.34")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation ("org.mapstruct:mapstruct:1.6.2")
    annotationProcessor ("org.mapstruct:mapstruct-processor:1.6.2")
    implementation("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    swaggerCodegen("io.swagger.codegen.v3:swagger-codegen-cli:3.0.47")
    implementation("io.swagger.core.v3:swagger-models-jakarta:2.2.26")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
    implementation("org.springframework.boot:spring-boot-starter-cache:3.4.1")
    // dependencies for swagger-codegen
    implementation("io.gsonfire:gson-fire:1.9.0")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.threeten:threetenbp:1.6.8")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.12")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.12")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    implementation("org.mapstruct:mapstruct:1.6.0.Beta1")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.0.Beta1")
    compileOnly("javax.servlet:servlet-api:3.0-alpha-1")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("net.logstash.logback:logstash-logback-encoder:7.2")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.25")

}

tasks.withType<Test> {
    useJUnitPlatform()
}

flyway {
    url = "jdbc:postgresql://localhost:5432/pracainzynierska_db"
    user = "postgres"
    password = "secret"
    schemas = arrayOf("public")
    locations = arrayOf("filesystem:src/main/resources/db/migration")
}

val apiPackage = "com.example.api"
val modelPackage = "com.example.model"
val swaggerFile = file("src/main/resources/contract/contract.yml")

swaggerSources {
    create("pracaInzynierskaApi") {
        setInputFile(swaggerFile)
        code.language = "spring"
        code.outputDir = file("$buildDir/generated-sources")
        code.components = listOf("apis", "models")
        code.additionalProperties = mapOf(
            "apiPackage" to apiPackage,
            "modelPackage" to modelPackage,
            "java8" to "true",
            "interfaceOnly" to "true",
            "useTags" to "true"
        )
    }
}

sourceSets {
    main {
        java {
            srcDir("$buildDir/generated-sources/src/main/java")
        }
    }
}
