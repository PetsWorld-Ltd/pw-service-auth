package site.pets.world.common.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@JvmInline
value class RefreshToken(val value: String = UUID.randomUUID().toString())