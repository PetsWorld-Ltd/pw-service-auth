package site.pets.world.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.SerializationException
import site.pets.world.common.dto.ErrorBody
import site.pets.world.common.dto.ErrorResponse
import site.pets.world.common.dto.ServerErrorResponse
import site.pets.world.utils.isCausedBy

fun Application.configureErrorHandling() {
    install(StatusPages) {
        exception<Throwable>(::handleException)
    }
}

private suspend fun <E : Throwable> handleException(call: ApplicationCall, cause: E) {
    when (cause) {
        is BadRequestException -> {
            when {
                cause.isCausedBy<SerializationException>() -> {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        ErrorResponse(ErrorBody.ParsingError)
                    )
                }
                else -> {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        ErrorResponse(ErrorBody.MalformedResponse)
                    )
                }
            }
        }
        else -> {
            call.respond(
                status = HttpStatusCode.InternalServerError,
                ServerErrorResponse()
            )
        }
    }
}