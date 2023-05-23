package site.pets.world.common.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ErrorResponse(
    @SerialName("error") val error: ErrorBody
)