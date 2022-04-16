import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Date

plugins {
    kotlin("jvm") version "1.4.21"
    application
    java
    maven
}

group = "com.hdkj"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    maven {
        url = uri("https://maven.aliyun.com/nexus/content/groups/public")
    }

    maven {
        url = uri("https://packages.aliyun.com/maven/repository/2031176-release-ZzZDVn/")
    }

    maven {
        url = uri("https://packages.aliyun.com/maven/repository/2031176-snapshot-jQdLt6/")
    }

    maven {
        url = uri("https://repo1.maven.org/maven2/")
    }
    mavenCentral()
}

dependencies {
    implementation("org.bouncycastle:bcprov-jdk16:1.45")
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}
application {
    mainClass.set("com.hdkj.util.MainKt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_14
    targetCompatibility = JavaVersion.VERSION_14
}

tasks.jar{
    manifest{
        attributes("Main-Class" to "com.hdkj.util.MainKt")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "14"
}

/**
 * 打包前更新版本
 */
tasks.register("updateVersion") {
    //获取版本java文件
    val versionFileDir =
        projectDir.absolutePath + File.separatorChar + "src" + File.separatorChar + "main" + File.separatorChar + "kotlin" + File.separatorChar + "com" + File.separatorChar + "hdkj" + File.separatorChar + "util" + File.separatorChar + "core" + File.separatorChar + "Constant.kt"
    //获取旧的版本
    val old = oldValue(versionFileDir, "VERSION")
    //找到版本号所在位置
    var index = old.indexOf("=")
    //生成新的版本
    val newVersion = old.substring(0, index + 1) + " \"" + version + "\""
    // 获得编译时间
    val now = Date()
    val oldDate = oldValue(versionFileDir, "BUILD_TIME")
    index = oldDate.indexOf("=")
    val date = oldDate.substring(0, index + 1) + " \"" + now + "\""
    //替换
    val updateContent = File(versionFileDir).readText().replace(old, newVersion).replace(oldDate, date)
    File(versionFileDir).writeText(updateContent)

}

fun oldValue(path: String, key: String): String {
    var readString = ""
    File(path).readLines().forEach {
        if (it.contains(key)) {
            readString = it
            return readString
        }
    }
    return readString
}

