package site.pets.world.admin.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdminAuthTokenResponse(
    @SerialName("accessToken")
    private val accessToken: String,
    @SerialName("refreshToken")
    private val refreshToken: String,
)