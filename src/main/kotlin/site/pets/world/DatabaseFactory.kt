package site.pets.world

import com.mongodb.reactivestreams.client.MongoClient
import kotlinx.serialization.modules.SerializersModule
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.id.jackson.IdKeySerializer
import org.litote.kmongo.id.serialization.IdKotlinXSerializationModule
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.serialization.registerModule
import org.litote.kmongo.serialization.registerSerializer

class DatabaseFactory(private val serviceConfig: ServiceConfig) {

    private lateinit var mongoClient: MongoClient
    private val coroutineClient: CoroutineClient
        get() = mongoClient.coroutine


    fun onCreate() {
        registerModule(IdKotlinXSerializationModule)
        mongoClient = KMongo.createClient(serviceConfig.mongoConnectionString)
    }

    fun getWebAdminDatabase(): CoroutineDatabase {
        return coroutineClient.getDatabase("WebAdmin")
    }

    companion object {
        const val DATABASE_WEB_ADMIN = "WebAdmin"
        const val DATABASE_ACCAUNTS = "Accounts"
    }
}





