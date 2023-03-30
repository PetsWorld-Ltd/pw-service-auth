package site.pets.world

data class ServiceConfig(
    val mongoConfig: MongoConfig,
) {
    companion object {
        val Default = ServiceConfig(
            mongoConfig = MongoConfig.Default,
        )
    }
}

data class MongoConfig(
    val mongoUser: String,
    val mongoPass: String,
    val mongoAddress: String,
    val mongoPort: String,
) {
    companion object {
        const val ENV_MONGO_USER = "MONGODB_USER"
        const val ENV_MONGO_PASS = "MONGODB_PASS"
        const val ENV_MONGO_ADDRESS = "MONGODB_ADDRESS"
        const val ENV_MONGO_PORT = "MONGODB_PORT"

        val Default = MongoConfig(
            mongoUser = "root",
            mongoPass = "example",
            mongoAddress = "localhost",
            mongoPort = "27017",
        )
    }
}


val ServiceConfig.mongoConnectionString: String
    get() = "mongodb://${mongoConfig.mongoUser}:${mongoConfig.mongoPass}@${mongoConfig.mongoAddress}:${mongoConfig.mongoPort}/"
