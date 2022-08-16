package suspend

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() { //runBlocking을 붙이지 않아도 됨
    val time = measureTimeMillis {
        // 코루틴 외부에서 비동기 작업을 시작시킬 수 있습니다
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        // 그러나 결과를 기다리는 것은 일시중지(suspending) 또는 blocking을 수반해야 합니다
        // 여기에서는 결과값을 대기하는 동안 `runBlocking { ... }`을 사용하여 main thread를 block 합니다
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")
}

//리턴 타입은 Deferred<Int>, suspend함수가 아니기 때문에 어디서든 호출 가능
private fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

//리턴 타입은 Deferred<Int>, suspend함수가 아니기 때문에 어디서든 호출 가능
private fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
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