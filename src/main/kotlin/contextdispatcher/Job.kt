package contextdispatcher

import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("My job is ${coroutineContext[Job]}") //job 역시 컨텍스트의 일부로써 job을 코드 안에서 꺼내올 수 있음

    println("My job is Active? : ${coroutineContext[Job]?.isActive == true}")

    println("My job is Active? : $isActive")
}