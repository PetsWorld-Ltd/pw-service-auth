package site.pets.world

import site.pets.world.admin.AdminRepository
import site.pets.world.admin.models.Administrator

interface Repositories {
    val adminRepository: AdminRepository
}

class MongoRepositories(databaseFactory: DatabaseFactory) : Repositories {

    override val adminRepository: AdminRepository by lazy {
        AdminRepository(
            databaseFactory.getWebAdminDatabase()
                .getCollection<Administrator>(COLLECTION_ADMINISTRATORS)
        )
    }

    companion object {
        const val COLLECTION_ADMINISTRATORS = "Administrators"
    }
}