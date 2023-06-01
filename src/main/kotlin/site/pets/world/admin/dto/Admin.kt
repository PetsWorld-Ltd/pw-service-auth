package site.pets.world.admin.dto

import kotlinx.serialization.Serializable
import site.pets.world.admin.models.Administrator

@Serializable
data class Admin(
    val login: String
) {
    constructor(admin: Administrator) : this(login = admin.login)
}