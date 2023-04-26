package site.pets.world.admin.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.id.ObjectIdGenerator
import org.litote.kmongo.newId
import site.pets.world.common.models.AccessToken
import site.pets.world.common.models.RefreshToken
import java.time.LocalDateTime

@Serializable
class AdminSession(
    @SerialName("_id")
    @Contextual
    val objId: ObjectId = ObjectId(),
    @SerialName("accessToken")
    val accessToken: AccessToken = AccessToken(),
    @SerialName("refreshToken")
    val refreshToken: RefreshToken = RefreshToken(),
    @SerialName("createdAt")
    @Contextual
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @SerialName("expiredAt")
    @Contextual
    val expiredAt: LocalDateTime = createdAt.plusHours(1L),
    @SerialName("isActive")
    val isActive: Boolean = true,
)