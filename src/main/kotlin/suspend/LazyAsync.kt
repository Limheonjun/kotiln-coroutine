package suspend

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking{
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }

//        one.start() // 첫 번째 코루틴 실행
//        two.start() // 두 번째 코루틴 실행
        println("The answer is ${one.await() + two.await()}")
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