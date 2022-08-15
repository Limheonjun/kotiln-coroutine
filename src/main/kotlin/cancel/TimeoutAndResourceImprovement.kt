package cancel

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import java.lang.Exception

var acquired = 0

class Resource {
    init { acquired++ } // 리소스 획득
    fun close() {
        println("1")
        acquired--
    } // 리소스 해제
}

fun main() {
    runBlocking {
        repeat(100_000) { // 10만개의 코루틴 실행
            launch {
                val resource = withTimeout(60) { // 60ms의 시간제한
                    delay(50) // 50ms간 Delay
                    Resource() // 리소스 획득 후, withTimeout 블럭에서 리턴
                }
                resource.close() // 리소스 해제
                /**
                 * 리소스 해제는 동작하지 않음
                 * 50ms의 딜레이 후 Resource를 획득하는 부분에서 timeout에 해당하는 60ms가 넘어감
                 * 따라서 CancellationException이 발생하여 획득 및 해제 둘 다 실행되지 않음
                 */
            }
        }
    }
    // runBlocking 외부, 모든 코루틴 완료(complete)
    println(acquired) // 여전히 획득(acquired)상태인 리소스의 개수 출력
}