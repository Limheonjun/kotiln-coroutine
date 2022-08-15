import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // 약간 delay
    println("main: I'm tired of waiting!")
    job.cancel() // job 취소
    job.join() // job의 완료를 대기
    println("main: Now I can quit.")
}