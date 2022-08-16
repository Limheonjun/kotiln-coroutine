package suspend

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking{
    val time = measureTimeMillis {
        val one = withContext(Dispatchers.Default) { doSomethingUsefulOne() }
        val two = withContext(Dispatchers.Default) { doSomethingUsefulTwo() }

        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")
}

//코루틴 안에서 실행될 코드는 suspend로 래핑
private suspend fun doSomethingUsefulOne(): Int {
    delay(1000L)
    return 13
}

//코루틴 안에서 실행될 코드는 suspend로 래핑
private suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L)
    return 29
}