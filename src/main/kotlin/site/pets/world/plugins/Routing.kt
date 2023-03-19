package site.pets.world.plugins

import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.serialization.Serializable
import java.io.OutputStream

fun Application.configureRouting() {
    install(Resources)
    routing {
        get("/") {
            call.respondText("Hello world!")
        }
        get<Articles> { article ->
            // Get all articles ...
            call.respond("List of articles sorted starting from ${article.sort}")
        }
        post("/echo") {
            call.respondText(
                contentType = call.request.contentType(),
                status = HttpStatusCode.OK,
                provider = { call.receiveText() },
            )
        }
    }
}

@Serializable
@Resource("/articles")
class Articles(val sort: String? = "new")
