package cancel

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

fun main() = runBlocking{
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } catch (e: Exception) {
            println("Exception : $e")
        } finally {
            println("job: I'm running finally")
        }
    }

    delay(1300L) // 약간 delay
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // Cancellation Exception을 던짐
    println("main: Now I can quit.")
}