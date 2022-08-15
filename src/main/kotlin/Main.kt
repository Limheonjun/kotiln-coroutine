import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking { // 메인 코루틴 시작
    /**
     * GlobalScope.launch : 프로세스가 죽으면 실행 안됨, 즉 실행 가능한 시간은 프로세스가 살아있는 동안만
     * launch : 내부에 선언된 코드들이 실행되기 전까지 프로세스가 죽지 않음
     */
    launch { // 새로운 코루틴을 백그라운드에서 실행 및 진행
        delay(1000L)
        println("heonjun")
    }
    println("lim")
//    delay(3000L) // runBlocking역시 코루틴 빌더이기 때문에 delay를 통해 일시 중지 가능
}