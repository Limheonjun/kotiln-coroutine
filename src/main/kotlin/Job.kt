import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    println("1")
    job1.join() // 하위 코루틴이 완료될 때까지 대기
    job2.join() // 하위 코루틴이 완료될 때까지 대기
}

val job1 = GlobalScope.launch { // 새로운 코루틴을 시작하고 해당 Job에 대한 참조를 유지
    delay(5000L)
    println("3")
}

val job2 = GlobalScope.launch { // 새로운 코루틴을 시작하고 해당 Job에 대한 참조를 유지
    delay(3000L)
    println("2")
}
