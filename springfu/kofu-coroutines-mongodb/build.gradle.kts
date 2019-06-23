plugins {
    id("org.springframework.boot") version Versions.spring_boot
}


dependencies {
    api(project(":kotlin-testcontainers"))

    api(Libraries.spring_fu_kofu)

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-mustache")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")

    implementation(Libraries.jackson_module_kotlin)
}