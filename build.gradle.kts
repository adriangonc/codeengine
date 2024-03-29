import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.4"
	id("io.spring.dependency-management") version "1.0.14.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	id("com.github.davidmc24.gradle.plugin.avro") version "1.2.0"
}

group = "com.codengine"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven {
		url = uri("https://packages.confluent.io/maven")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.projectlombok:lombok:1.18.20")
	// Required for MockMvc autoconfigure
	testImplementation("org.springframework.boot:spring-boot-starter-web")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("junit:junit:4.13.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
	testImplementation("io.projectreactor:reactor-test")

	//Junit
	testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api")
	testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.0")
	testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine")
	//mock
	testImplementation(group = "io.mockk", name = "mockk", version = "1.10.2")
	//Cache redis
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-redis-reactive
	implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive:2.7.5")
	// https://mvnrepository.com/artifact/redis.clients/jedis
	//implementation("redis.clients:jedis:4.3.1")
	// https://mvnrepository.com/artifact/org.slf4j/slf4j-api
	implementation("org.slf4j:slf4j-api:1.7.25")
	// https://mvnrepository.com/artifact/com.google.code.gson/gson
	implementation("com.google.code.gson:gson:2.10")
	// https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
	implementation("org.apache.commons:commons-lang3:3.8.1")

	//RabbitMQ
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	
	//Sleuth - atribui id para logs / mensagens
	// https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-sleuth
	implementation("org.springframework.cloud:spring-cloud-starter-sleuth:3.1.4")

	//Kafka
	// https://mvnrepository.com/artifact/org.springframework.kafka/spring-kafka
	implementation("org.springframework.kafka:spring-kafka:2.9.4")

	//Avro
	implementation("io.confluent:kafka-avro-serializer:5.5.0")
	implementation("org.apache.avro:avro:1.10.0")
	//implementation("org.apache.avro:avro:1.11.0")
	//implementation("org.apache.avro:avro-tools:1.11.0")
	//implementation("io.confluent:kafka-avro-serializer:7.3.1")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.named("compileKotlin") {
	dependsOn(":generateAvroJava")
}

sourceSets {
	main {
		java.srcDir("main/avro")
	}
}

avro {
	fieldVisibility.set("PRIVATE")
	isCreateSetters.set(false)
	outputCharacterEncoding.set("UTF-8")
}
