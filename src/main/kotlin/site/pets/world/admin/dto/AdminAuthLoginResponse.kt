package site.pets.world.admin.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import site.pets.world.admin.models.AdminSession

@Serializable
class AdminAuthLoginResponse(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
) {

    constructor(adminSession: AdminSession): this(
        accessToken = adminSession.accessToken.value,
        refreshToken = adminSession.refreshToken.value,
    )
}