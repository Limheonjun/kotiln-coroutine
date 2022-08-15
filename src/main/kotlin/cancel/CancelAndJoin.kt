import kotlinx.coroutines.*

fun main() = runBlocking{
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // CPU만 낭비하는 computation loop
            // 초당 메시지 두 번 출력
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // 약간 delay
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // job 취소 후, 완료될 때까지 대기
    println("main: Now I can quit.")
}