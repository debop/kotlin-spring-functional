package io.github.debop.kotlin.testcontainers

import com.mongodb.MongoClient
import mu.KLogging
import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldNotBeNull
import org.bson.Document
import org.junit.jupiter.api.Test

class MongoDBContainerTest {

    companion object: KLogging() {
        val mongodb: MongoDBContainer = MongoDBContainer.instance
    }

    @Test
    fun `create mongodb testcontainer instance`() {
        mongodb.shouldNotBeNull()
        logger.debug { "MongoDB host=${mongodb.host}, port=${mongodb.port}" }
    }

    @Test
    fun `connect to mongodb`() {
        val client = MongoClient(mongodb.host, mongodb.port)

        client.listDatabaseNames().forEach { database ->
            logger.debug { "Database name=$database" }

            val db = client.getDatabase(database)
            db.listCollectionNames().forEach { collection ->
                logger.debug { "\tCollection=$collection" }
            }
        }
    }

    @Test
    fun `save and read with customer collection`() {
        val client = MongoClient(mongodb.host, mongodb.port)

        val db = client.getDatabase("local")
        db.createCollection("customers")
        val customers = db.getCollection("customers")

        val document = Document().apply {
            put("name", "Debop")
            put("company", "Coupang")
        }
        customers.insertOne(document)

        val loaded = customers.find().toList()
        loaded.size shouldEqualTo 1
    }
}