import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "1.8.22"
}

group = "com.cesarlucasjunior"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework:spring-jms:6.0.11")
	implementation("software.amazon.awssdk:sqs:2.20.139")
	implementation("com.amazonaws:amazon-sqs-java-messaging-lib:2.1.1")
	testImplementation("io.mockk:mockk:1.13.7")
	implementation("io.github.serpro69:kotlin-faker:1.15.0-rc.1")
	implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
//	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")
//	implementation("org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:1.6.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
