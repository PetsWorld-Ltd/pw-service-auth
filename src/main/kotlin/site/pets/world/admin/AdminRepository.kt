package site.pets.world.admin

import com.mongodb.client.model.UpdateOptions
import org.bson.Document
import org.litote.kmongo.EMPTY_BSON
import org.litote.kmongo.coroutine.CoroutineCollection
import site.pets.world.admin.models.AdminSession
import site.pets.world.admin.models.Administrator
import site.pets.world.common.models.AccessToken
import site.pets.world.common.models.RefreshToken

class AdminRepository(private val col: CoroutineCollection<Administrator>) {

    suspend fun getAdministrators(): List<Administrator> {
        return col.find(EMPTY_BSON).toList()
    }

    suspend fun findAdministratorByLogin(login: String): Administrator? {
        return col.findOne("{ login: '${login}'}")
    }

    suspend fun findAdministratorByToken(accessToken: AccessToken): Administrator? {
        return col.findOne("{ sessions.accessToken: '${accessToken.value}'}")
    }

    suspend fun findAdministratorByToken(refreshToken: RefreshToken): Administrator? {
        return col.findOne("{ sessions.refreshToken: '${refreshToken.value}'}")
    }

    suspend fun createSession(administrator: Administrator): AdminSession {
        val newSession = AdminSession()
        col.updateOne(
            filter = "{ login: '${administrator.login}'}",
            update = Document("\$push", Document("sessions", newSession))
        )
        return newSession
    }

    suspend fun createSession(refreshToken: RefreshToken): AdminSession? {
        val newSession = AdminSession()

        val updateResult = col.updateOne(
            filter = Document(
                mapOf(
                    "sessions.refreshToken" to Document("\$eq", refreshToken.value),
                    "sessions.isActive" to Document("\$eq", false),
                )
            ),
            update = Document(
                mapOf("\$push" to Document("sessions", newSession))
            ),
        )
        return if (updateResult.modifiedCount == 1L) newSession else null
    }

    suspend fun expireSession(refreshToken: RefreshToken) {
        col.updateOne(
            filter = "{ }",
            update = Document("\$set", Document("sessions.$[elem].isActive", false)),
            options = UpdateOptions().arrayFilters(
                listOf(
                    Document(
                        mapOf(
                            "elem.refreshToken" to Document("\$eq", refreshToken.value),
                            "elem.isActive" to Document("\$eq", true),
                        )
                    ),
                )
            ),
        )
    }

    suspend fun expireSession(accessToken: AccessToken) {
        col.updateOne(
            filter = "{ sessions.accessToken: '${accessToken.value}'}",
            update = "{ sessions.isActive: 'false' }",
        )
    }
}