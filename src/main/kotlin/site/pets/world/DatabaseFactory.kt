package site.pets.world

import com.mongodb.reactivestreams.client.MongoClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

class DatabaseFactory(private val serviceConfig: ServiceConfig) {
    private lateinit var database: CoroutineDatabase

    fun onCreate() {
        val mongoClient: MongoClient = KMongo.createClient(serviceConfig.mongoConnectionString)
        val client = mongoClient.coroutine
        database = client.getDatabase("WebAdmin")
    }
}





