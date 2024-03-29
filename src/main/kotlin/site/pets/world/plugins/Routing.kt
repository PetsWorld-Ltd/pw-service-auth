package site.pets.world.plugins

import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.serialization.Serializable
import site.pets.world.AuthApp
import site.pets.world.admin.admin

fun Application.configureRouting(authApp: AuthApp) {
    install(Resources)
    routing {
        get("/") {
            call.respondText("Hello world! #${authApp.getRootRequestNumber()}")
        }

        swaggerUI("/swagger", swaggerFile = "service-api.yaml") {
            install(CORS) {
                anyHost()
                allowHeader(HttpHeaders.ContentType)
            }
        }

        route("/api") {
            admin(authApp.repositories)
        }
    }
}

@Serializable
@Resource("/articles")
class Articles(val sort: String? = "new")
