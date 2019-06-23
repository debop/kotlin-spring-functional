package io.github.debop.springfu.coroutines

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.core.io.ClassPathResource
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations
import org.springframework.data.mongodb.core.allAndAwait
import org.springframework.data.mongodb.core.asType
import org.springframework.data.mongodb.core.awaitCount
import org.springframework.data.mongodb.core.awaitOneOrNull
import org.springframework.data.mongodb.core.findReplaceAndAwait
import org.springframework.data.mongodb.core.insert
import org.springframework.data.mongodb.core.oneAndAwait
import org.springframework.data.mongodb.core.query
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.mongodb.core.remove
import org.springframework.data.mongodb.core.update

/**
 * Repositories
 *
 * @author debop (Sunghyouk Bae)
 * @since 19. 6. 22
 */

class UserRepository(private val mongo: ReactiveFluentMongoOperations,
                     private val objectMapper: ObjectMapper) {

    suspend fun count() = mongo.query<User>().awaitCount()

    suspend fun findAll(): MutableList<User> =
        mongo.query<User>().all().collectList().awaitSingle()

    suspend fun findOne(id: String): User? =
        mongo.query<User>().matching(query(where("id").isEqualTo(id))).awaitOneOrNull()

    suspend fun deleteAll() {
        mongo.remove<User>().allAndAwait()
    }

    suspend fun insert(user: User): User =
        mongo.insert<User>().oneAndAwait(user)

    suspend fun update(user: User): User =
        mongo.update<User>().replaceWith(user).asType<User>().findReplaceAndAwait()

    suspend fun init() {
        val eventsResource = ClassPathResource("data/users.json")
        val users: List<User> = objectMapper.readValue(eventsResource.inputStream)

        users.forEach { insert(it) }
    }
}