package site.pets.world.utils

import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

inline fun <reified E : Throwable> Throwable.isCausedBy(): Boolean {
    return isCausedBy(E::class)
}

fun Throwable.isCausedBy(target: KClass<out Throwable>): Boolean {
    var cause = cause
    while (cause != null) {
        if (cause::class.isSubclassOf(target)) {
            return true
        }
        cause = cause.cause
    }
    return false
}

