package suspend

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking{
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")
}

//코루틴 스코프를 만들고 내부에서 비동기함수를 수행하도록함
suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
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