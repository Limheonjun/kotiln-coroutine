package suspend

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    try {
        failedConcurrentSum()
    } catch(e: ArithmeticException) {
        println("Computation failed with ArithmeticException")
    }
}

suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // 매우 긴 연산을 모방합니다
            42
        } finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        delay(2000)
        println("Second child throws an exception")
        throw ArithmeticException()
    }
    one.await() + two.await()
}