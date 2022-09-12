package contextdispatcher

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main(): Unit = runBlocking {
    val a = async {
        logger.info("I'm computing a piece of the answer")
        6
    }
    val b = async {
        logger.info("I'm computing another piece of the answer")
        7
    }
    logger.info("The answer is ${a.await() * b.await()}")
}