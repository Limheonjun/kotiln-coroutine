package contextdispatcher

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val request = launch {
        // 두 개의 다른 job을 생성하며, 하나는 GlobalScope에서 생성
        GlobalScope.launch {
            println("job1: I run in GlobalScope and execute independently!")
            delay(10000)
            println("job1: I am not affected by cancellation of the request")
        }
        // 다른 하나는 상위 context를 상속
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
    }
    delay(500)
    request.cancel() // request 처리 취소
    delay(1000) // 어떠한 일이 일어나는 지 확인하기 위해 1초간 delay
    println("main: Who has survived request cancellation?")
}