package site.pets.world.admin.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import site.pets.world.common.dto.ErrorBody

@Serializable
class AdminAuthLoginBadRequestResponse(
    @SerialName("error")
    val error: ErrorBody? = null
)