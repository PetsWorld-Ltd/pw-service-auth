package site.pets.world

import com.mongodb.reactivestreams.client.MongoClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import site.pets.world.admin.models.Administrator

object DatabaseFactory {
    private lateinit var database: CoroutineDatabase

    fun init(config: ServiceConfig = ServiceConfig) {
        try {
            val mongoClient: MongoClient = KMongo.createClient(config.mongoConnectionString)
            val client = mongoClient.coroutine
            database = client.getDatabase("WebAdmin")
        } catch (e: Exception) {
            println("Unfortunately database fails to init: ${e.message}")
            e.printStackTrace()
        }
    }

    val administrators: CoroutineCollection<Administrator> by lazy { database.getCollection("Administrators") }

}





