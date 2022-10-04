fun main() {
    converter(str1 = "David", str2 = "divaD")
}

fun converter(str1: String, str2: String){
    val result: Boolean = str1.contains(str2.reversed())
    println(result)
}
