import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking { // 내부에 있는 모든 코루틴이 끝날때까지 스레드가 종료되지 않음
    launch {
        delay(200L)
        println("${Thread.currentThread().name} : Task from runBlocking")
    }
    coroutineScope { // coroutine scope 생성과 동시에 runBlocking 내부의 코드 일시중지
        launch {
            delay(500L)
            println("${Thread.currentThread().name} : Task from nested launch")
        }
        delay(100L)
        println("${Thread.currentThread().name} : Task from coroutine scope") // 해당 코드라인은 중첩된 launch 이전에 출력됩니다.
    }
    println("${Thread.currentThread().name} : Coroutine scope is over") // coroutineScope내부의 모든 코루틴이 종료되어 coroutineScope가 끝나면 실행됨
}
