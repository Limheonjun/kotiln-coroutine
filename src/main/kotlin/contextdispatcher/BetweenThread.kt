package contextdispatcher

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import mu.KotlinLogging
private val logger = KotlinLogging.logger {}

fun main(): Unit = runBlocking {
    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                logger.info("Started in ctx1")
                withContext(ctx2) {
                    logger.info("Working in ctx2")
                }
                logger.info("Back to ctx1")
            }
        }
    }
}