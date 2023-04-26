package site.pets.world.admin.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.BsonDateTime
import org.bson.codecs.ObjectIdGenerator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.id.ObjectIdGenerator.newObjectId
import org.litote.kmongo.newId
import java.time.LocalDateTime

@Serializable
class Administrator(
    @Contextual
    @BsonId
    @SerialName("_id")
    val objId: ObjectId = ObjectId(),
    @SerialName("login")
    val login: String,
    @SerialName("passwordHash")
    val passwordHash: String,
    @SerialName("createdAt")
    @Contextual
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @SerialName("updatedAt")
    @Contextual
    val updatedAt: LocalDateTime? = null,
    @SerialName("isActive")
    val isActive: Boolean = true,
    @SerialName("sessions")
    val sessions: List<AdminSession> = emptyList(),
)