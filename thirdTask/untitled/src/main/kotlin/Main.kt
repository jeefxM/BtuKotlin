import java.time.LocalDateTime

fun main(args: Array<String>) {
    val current = LocalDateTime.now()

    val stringyfiedTime = current.getdate()
    println(stringyfiedTime)
    checkString(stringyfiedTime)
}

fun LocalDateTime.getdate(): String {
    return this.toString()
}

fun checkString(time: String){
    println(time)
}

