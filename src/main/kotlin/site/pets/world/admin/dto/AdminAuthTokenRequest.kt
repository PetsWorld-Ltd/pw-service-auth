package site.pets.world.admin.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdminAuthTokenRequest(
    @SerialName("refreshToken")
    val refreshToken: String,
)