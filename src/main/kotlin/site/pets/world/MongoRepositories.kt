package site.pets.world

import org.litote.kmongo.coroutine.CoroutineCollection
import site.pets.world.admin.AdminRepository
import site.pets.world.admin.models.Administrator

interface Repositories {
    val adminRepository: AdminRepository
}

class MongoRepositories(databaseFactory: DatabaseFactory) : Repositories {

    private val administratorsCollection: CoroutineCollection<Administrator> by lazy {
        databaseFactory.getWebAdminDatabase()
            .getCollection<Administrator>(COLLECTION_ADMINISTRATORS)
    }

    override val adminRepository: AdminRepository by lazy { AdminRepository(administratorsCollection) }

    companion object {
        const val COLLECTION_ADMINISTRATORS = "Administrators"
    }
}