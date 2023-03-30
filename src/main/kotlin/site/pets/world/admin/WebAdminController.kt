package site.pets.world.admin

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import site.pets.world.MongoRepositories
import site.pets.world.Repositories
import site.pets.world.admin.dto.*
import site.pets.world.common.dto.ErrorBody

fun Route.admin(repositories: Repositories) {
    route("/v1/admin") {
        adminLogin(repositories)
        updateToken(repositories)
    }
}

private fun Route.adminLogin(repositories: Repositories) {
    post("login") {
        val request = call.receive<AdminAuthLoginRequest>()
        val adminRepository = repositories.adminRepository
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
                AdminAuthLoginResponse(
                    accessToken = session.accessToken,
                    refreshToken = session.refreshToken,
                )
            )
        }
    }
}

private fun Route.updateToken(repositories: Repositories) {
    post("token") {
        val request = call.receive<AdminAuthTokenRequest>()
        val adminRepository = repositories.adminRepository
        val admin = adminRepository.findAdministratorByToken(request.refreshToken)
        if (admin == null) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                AdminAuthTokenBadRequestResponse(
                    ErrorBody("Session with refreshToke '${request.refreshToken}' no found")
                )
            )
        } else {
            val session = adminRepository.updateSession(admin, request.refreshToken)
            call.respond(
                status = HttpStatusCode.OK,
                AdminAuthTokenResponse(
                    accessToken = session.accessToken,
                    refreshToken = session.refreshToken,
                )
            )
        }
    }
}

private fun Route.accounts(repositories: Repositories) {
    get("accounts") {
        TODO()
    }
}

