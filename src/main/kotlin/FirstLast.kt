import io.reactivex.Observable

fun main(args: Array<String>) {
    val list = listOf<Int>()
//    Observable.fromIterable(mutableListOf(1, 2, 2, 3, 3, 3, 7, 8, 9, 10))
    Observable.fromIterable(list)
            .firstElement()
            .subscribe({ item -> System.out.println(item) }, {
                println(it.message)
            })

    Observable.fromIterable(mutableListOf(1, 2, 2, 3, 3, 3, 7, 8, 9, 10))
            .lastElement()
            .subscribe({ item -> System.out.println(item) })
}