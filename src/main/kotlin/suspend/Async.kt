package suspend

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }//one이 종료될때까지 기다리지 않고 바로 실행 => 동시에 진행됨
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")//one과 two 중 더 오래걸린 시간이 최종 시간
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


