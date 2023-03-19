package site.pets.world

interface ServiceConfig {

    val mongoUser: String
    val mongoPass: String
    val mongoAddress: String
    val mongoPort: String

    companion object : ServiceConfig {
        override val mongoUser: String = "root"
        override val mongoPass: String = "example"
        override val mongoAddress: String = "localhost"
        override val mongoPort: String = "27017"
    }
}

val ServiceConfig.mongoConnectionString: String
    get() = "mongodb://$mongoUser:$mongoPass@$mongoAddress:$mongoPort/"
