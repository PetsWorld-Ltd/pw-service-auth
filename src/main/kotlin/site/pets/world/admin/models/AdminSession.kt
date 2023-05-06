package site.pets.world.admin.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import site.pets.world.common.models.AccessToken
import site.pets.world.common.models.RefreshToken
import site.pets.world.plus
import java.time.Instant
import kotlin.time.Duration.Companion.hours

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
    val createdAt: Instant = Instant.now(),
    @SerialName("expiredAt")
    @Contextual
    val expiredAt: Instant = createdAt + 1.hours,
    @SerialName("isActive")
    val isActive: Boolean = true,
)