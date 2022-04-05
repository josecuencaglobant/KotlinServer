import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
}

group = "com.sim"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17



repositories {
	mavenCentral()
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	//implementation("android.arch.persistence.room:runtime:1.1.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	//Changes in RunTime
	implementation("org.springframework.boot:spring-boot-devtools:2.6.5")

	//Swager 3.0.0
	//https://github.com/hendisantika/swagger-kotlin
	//https://springfox.github.io/springfox/docs/current/#getting-started
	//http://localhost:8080/swagger-ui/
	implementation ("io.springfox:springfox-boot-starter:3.0.0")
	implementation("io.springfox:springfox-swagger-ui:3.0.0")
	implementation("io.springfox:springfox-swagger2:3.0.0")

	//Postgress
	implementation("org.postgresql:postgresql:42.3.3")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

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
