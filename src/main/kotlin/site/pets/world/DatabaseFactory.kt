package site.pets.world

import com.mongodb.reactivestreams.client.MongoClient
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.overwriteWith
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.id.serialization.IdKotlinXSerializationModule
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.serialization.SerializationClassMappingTypeService
import org.litote.kmongo.serialization.kmongoSerializationModule
import org.litote.kmongo.serialization.registerModule
import java.time.Instant

class DatabaseFactory(private val serviceConfig: ServiceConfig) {

    private lateinit var mongoClient: MongoClient
    private val coroutineClient: CoroutineClient
        get() = mongoClient.coroutine


    fun onCreate() {
        registerModule(IdKotlinXSerializationModule)

        registerModule(kmongoSerializationModule.overwriteWith(
            SerializersModule {
                contextual(Instant::class, InstantSerializer)
            }
        ))
        installKotlinxSerializer()
        mongoClient = KMongo.createClient(serviceConfig.mongoConnectionString)
    }

    fun getWebAdminDatabase(): CoroutineDatabase {
        return coroutineClient.getDatabase("WebAdmin")
    }

    companion object
}

fun installKotlinxSerializer() {
    System.setProperty("org.litote.mongo.mapping.service", SerializationClassMappingTypeService::class.qualifiedName!!)
}

object InstantSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("pw.InstantSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Instant {
        return Instant.parse(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeString(value.toString())
    }
}