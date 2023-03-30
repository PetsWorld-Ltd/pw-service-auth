package site.pets.world.admin.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdminAuthLoginRequest(
    @SerialName("login")
    val login: String,
    @SerialName("password")
    val password: String
)