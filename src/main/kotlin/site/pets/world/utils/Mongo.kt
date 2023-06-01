package site.pets.world.utils

import com.mongodb.client.model.UpdateOptions
import org.bson.Document

fun UpdateOptions.arrayFilters(vararg documents: Document): UpdateOptions {
    return arrayFilters(listOf(*documents))
}

fun Document(vararg content: Pair<String, Any>): Document {
    return Document(mapOf(*content))
}

object MongoOps {
    const val eq = "\$eq"
    const val set = "\$set"
    const val push = "\$push"
}