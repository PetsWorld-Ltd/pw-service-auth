package site.pets.world.admin.schema

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.BsonDateTime
import org.bson.BsonTimestamp
import org.litote.kmongo.Id
import org.litote.kmongo.newId

@Serializable
class AdminSession(
    @SerialName("_id")
    val objId: Id<AdminSession> = newId(),
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
    @SerialName("createdAt")
    @Contextual
    val createdAt: BsonDateTime = BsonDateTime(System.currentTimeMillis()),
    @SerialName("expiredAt")
    @Contextual
    val expiredAt: BsonDateTime = BsonDateTime(System.currentTimeMillis() + 3600 * 1000),
    @SerialName("isActive")
    val isActive: Boolean = true,
)