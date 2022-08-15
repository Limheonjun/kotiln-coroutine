package cancel

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

var acquired2 = 0

class Resource2 {
    init { acquired2++ } // 리소스 획득
    fun close() {
        println("1")
        acquired2--
    } // 리소스 해제
}

fun main() {
    runBlocking {
        repeat(100_000) { // 코루틴 10만개 실행
            launch {
                var resource: Resource? = null // 아직 리소스 미획득
                try {
                    withTimeout(60) { // 60 ms의 시간제약
                        delay(50) // 50 ms간 딜레이
                        resource = Resource() // 리소스 획득 시, 변수에 리소스 저장
                    }
                    // 획득한 리소스로 다른 작업 가능
                } finally {
                    resource?.close() // 획득했던 리소스 해제
                }
            }
        }
    }
    // runBlocking 외부, 모든 코루틴 완료(complete)
    println(acquired) // 여전히 획득(acquired)상태인 리소스의 개수 출력
}