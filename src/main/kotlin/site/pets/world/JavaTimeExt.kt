package site.pets.world

import java.time.Instant
import kotlin.time.Duration

operator fun Instant.plus(duration: Duration): Instant {
    return this.plusMillis(duration.inWholeMilliseconds)
}