@file:JvmName("ApplicationKt")

package site.pets.world

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.logging.*
import io.ktor.server.netty.EngineMain as NettyEngineMain
import site.pets.world.plugins.*

fun main(args: Array<String>) {
    NettyEngineMain.main(args)
}

@Suppress("unused") // Configured to launch via application.yml in resources
fun Application.module() {
    val authApp = AuthApp(this)
    authApp.onCreate()
    configureErrorHandling()
    configureSecurity()
    configureAdministration()
    configureSerialization()
    configureMonitoring()
    configureRouting(authApp)
}

