package suspend

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking{
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()//one이 종료되고 나서야 실행가능
        println("The answer is ${one + two}")
    }
    println("Completed in $time ms") // 총 시간은 one에 걸린시간 + two에 걸린시간
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