plugins {
	java
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "com.sunhive"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	runtimeOnly ("com.h2database:h2")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-web-services")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	implementation("com.google.code.gson:gson:2.10.1")
	implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
	implementation("org.postgresql:postgresql:42.6.0")
	implementation("org.webjars:bootstrap:5.3.2")
<<<<<<< HEAD

	//developmentOnly("org.springframework.boot:spring-boot-devtools")
	//implementation("org.springframework.boot:spring-boot-starter-actuator")
	//implementation("org.springframework.boot:spring-boot-starter-security")
	//implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	//implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	//implementation("org.springframework.boot:spring-boot-starter-web")
	//implementation("org.springframework.boot:spring-boot-starter-web-services")

	//providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	//testImplementation("org.springframework.boot:spring-boot-starter-test")
	//testImplementation("io.projectreactor:reactor-test")
	//implementation("com.google.code.gson:gson:2.10.1")
	//implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
	//implementation("org.postgresql:postgresql:42.6.0")
	//implementation("org.webjars:bootstrap:5.3.2")

=======
>>>>>>> f57df8a8dd5f0c82f50e3db62e9e32c213ea0d52
	implementation("com.fazecast:jSerialComm:2.10.4") // Serial stuff

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("org.postgresql:postgresql")

}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootBuildImage {
	builder.set("paketobuildpacks/builder-jammy-base:latest")
}
