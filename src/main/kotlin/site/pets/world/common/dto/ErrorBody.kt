package site.pets.world.common.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorBody(
    @SerialName("message")
    val message: String? = null
) {
    companion object {
        val MalformedResponse = ErrorBody("Malformed request")
        val ParsingError = ErrorBody("Couldn't parse request content")
    }
}