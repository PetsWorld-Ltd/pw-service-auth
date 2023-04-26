package site.pets.world.admin.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import site.pets.world.admin.models.AdminSession

@Serializable
data class AdminAuthTokenResponse(
    @SerialName("accessToken")
    private val accessToken: String,
    @SerialName("refreshToken")
    private val refreshToken: String,
) {
    constructor(sessions: AdminSession) : this(
        accessToken = sessions.accessToken.value,
        refreshToken = sessions.refreshToken.value,
    )
}