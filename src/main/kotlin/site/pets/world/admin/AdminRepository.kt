package site.pets.world.admin

import org.bson.BsonDateTime
import org.litote.kmongo.EMPTY_BSON
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import site.pets.world.admin.schema.AdminSession
import site.pets.world.admin.schema.Administrator
import java.util.UUID

class AdminRepository(private val administratorsCollection: CoroutineCollection<Administrator>) {

    suspend fun getAdministrators(): List<Administrator> {
        return administratorsCollection.find(EMPTY_BSON)
            .toList()
    }

    suspend fun findAdministratorByLogin(login: String): Administrator? {
        return administratorsCollection.findOne(Administrator::login eq login)
    }

    suspend fun findAdministratorByToken(refreshToken: String): Administrator? {
        return Administrator(
            login = "admin",
            passwordHash = "admin",
        )
    }

    suspend fun createSession(administrator: Administrator): AdminSession {
        return AdminSession(
            accessToken = UUID.randomUUID().toString(),
            refreshToken = UUID.randomUUID().toString(),
        )
    }

    suspend fun updateSession(administrator: Administrator, refreshToken: String): AdminSession {
        return AdminSession(
            accessToken = UUID.randomUUID().toString(),
            refreshToken = UUID.randomUUID().toString(),
        )
    }



}