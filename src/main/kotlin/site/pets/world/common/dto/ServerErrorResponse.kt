package site.pets.world.common.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ServerErrorResponse(
    @SerialName("error") val error: ErrorBody = ErrorBody("Unexpected error")
)