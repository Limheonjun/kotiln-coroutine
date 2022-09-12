package contextdispatcher

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    launch { // 상위 컨텍스트, main runBlocking 코루틴
        println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) { // 제한되지 않음 -- 메인 스레드와 함께 실행
        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) { // DefaultDispatcher에서 사용
        println("Default               : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(newSingleThreadContext("HJ_THREAD")) { // 자체 새로운 스레드 사용
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }
}