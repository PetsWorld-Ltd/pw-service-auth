package site.pets.world

import io.ktor.server.application.*
import io.ktor.util.logging.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.exitProcess

class AuthApp(private val application: Application) : Logger by application.log {

    private val rootRequestNumber = AtomicInteger(1)

    fun getRootRequestNumber(): Int {
        return rootRequestNumber.getAndIncrement()
    }

    lateinit var serviceConfig: ServiceConfig
        private set

    lateinit var databaseFactory: DatabaseFactory
        private set

    fun onCreate() {
        serviceConfig = onInitServiceConfig()
//        databaseFactory = onInitDatabaseFactory()
    }

    private fun onInitServiceConfig(): ServiceConfig {
        val missedVars = mutableSetOf<String>()
        val mongoUser = System.getenv(MongoConfig.ENV_MONGO_USER)
        if (mongoUser.isNullOrBlank()) {
            missedVars += MongoConfig.ENV_MONGO_USER
        }
        val mongoPass = System.getenv(MongoConfig.ENV_MONGO_PASS)
        if (mongoPass.isNullOrBlank()) {
            missedVars += MongoConfig.ENV_MONGO_PASS
        }
        val mongoAddress = System.getenv(MongoConfig.ENV_MONGO_ADDRESS)
        if (mongoAddress.isNullOrBlank()) {
            missedVars += MongoConfig.ENV_MONGO_ADDRESS
        }
        val mongoPort = System.getenv(MongoConfig.ENV_MONGO_PORT)
        if (mongoPort.isNullOrBlank()) {
            missedVars += MongoConfig.ENV_MONGO_PORT
        }
        return if (missedVars.isNotEmpty()) {
            warn("Missed environment variables $missedVars; Default config will be used: ${ServiceConfig.Default}")
            ServiceConfig.Default
        } else
            ServiceConfig(
                mongoConfig = MongoConfig(
                    mongoUser = mongoUser,
                    mongoPass = mongoPass,
                    mongoAddress = mongoAddress,
                    mongoPort = mongoPort,
                )
            )
    }

    private fun onInitDatabaseFactory(): DatabaseFactory {
        val databaseFactory = DatabaseFactory(serviceConfig)
        try {
            databaseFactory.onCreate()
        } catch (ex: Throwable) {
            error("Fail to initialize database access; App will be terminated", ex)
            exitProcess(100)
        }
        return databaseFactory
    }

}