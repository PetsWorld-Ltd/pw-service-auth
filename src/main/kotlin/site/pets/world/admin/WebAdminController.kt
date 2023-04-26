package site.pets.world.admin

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import site.pets.world.Repositories
import site.pets.world.admin.dto.*
import site.pets.world.common.dto.ErrorBody
import site.pets.world.common.models.RefreshToken

fun Route.admin(repositories: Repositories) {
    route("/v1/admin") {
        route("auth") {
            adminLogin(repositories.adminRepository)
            updateToken(repositories.adminRepository)
        }
    }
}

private fun Route.adminLogin(adminRepository: AdminRepository) {
    post("login") {
        val request = call.receive<AdminAuthLoginRequest>()
        val admin = adminRepository.findAdministratorByLogin(request.login)
        if (admin == null) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                AdminAuthLoginBadRequestResponse(
                    ErrorBody("Admin with name '${request.login}' not found")
                )
            )
        } else if (admin.passwordHash != request.password) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                AdminAuthLoginBadRequestResponse(
                    ErrorBody("Passed password is invalid")
                )
            )
        } else {
            val session = adminRepository.createSession(admin)
            call.respond(
                status = HttpStatusCode.OK,
                AdminAuthLoginResponse(session),
            )
        }
    }
}

private fun Route.updateToken(adminRepository: AdminRepository) {
    post("token") {
        val request = call.receive<AdminAuthTokenRequest>()
        val refreshToken = RefreshToken(request.refreshToken)
        adminRepository.expireSession(refreshToken)
        val session = adminRepository.createSession(refreshToken)
        if (session == null) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                AdminAuthTokenBadRequestResponse(
                    ErrorBody("Session with refreshToken '${request.refreshToken}' not found")
                )
            )
        } else {
            call.respond(
                status = HttpStatusCode.OK,
                AdminAuthTokenResponse(session)
            )
        }
    }
}

private fun Route.accounts(repositories: Repositories) {
    get("accounts") {
        TODO()
    }
}

