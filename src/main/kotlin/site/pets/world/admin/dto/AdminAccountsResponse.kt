package site.pets.world.admin.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AdminAccountsResponse(
    @SerialName("accounts") val accounts: List<Admin>
)