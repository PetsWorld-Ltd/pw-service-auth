package site.pets.world.admin.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.BsonDateTime
import org.litote.kmongo.Id
import org.litote.kmongo.newId

@Serializable
class Administrator(
    @Contextual
    @SerialName("_id")
    val objId: Id<Administrator> = newId(),
    @SerialName("login")
    val login: String,
    @SerialName("passwordHash")
    val passwordHash: String,
    @SerialName("createdAt")
    @Contextual
    val createdAt: BsonDateTime = BsonDateTime(System.currentTimeMillis()),
    @SerialName("updatedAt")
    @Contextual
    val updatedAt: BsonDateTime? = null,
    @SerialName("isActive")
    val isActive: Boolean = true,
    @SerialName("sessions")
    val sessions: List<AdminSession> = emptyList(),
)