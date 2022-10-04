fun main(args: Array<String>) {
    getEvenNumber(arrayname)
}
val arrayname = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 122)

fun getEvenNumber(args: Array<Int>){
    var myList = args
    var evenNumbers = myList.filter { x -> x % 2 == 0 }
    println("Even number list : ${evenNumbers}")
}