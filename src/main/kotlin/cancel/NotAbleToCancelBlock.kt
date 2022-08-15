package cancel

import kotlinx.coroutines.*

//취소 불가능한 블럭 실행
fun main() = runBlocking{
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            withContext(NonCancellable) {
                println("job: I'm running finally")
                delay(1000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L) // 약간 delay
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // job 취소 후, 완료될 때까지 대기
    println("main: Now I can quit.")
}