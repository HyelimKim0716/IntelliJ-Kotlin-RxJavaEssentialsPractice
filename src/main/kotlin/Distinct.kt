import io.reactivex.Observable

fun main(args: Array<String>) {
    val list = mutableListOf(1, 2, 2, 3, 3, 3, 7, 8, 9, 10, 10 , 11, 10, 11 )
    Observable.fromIterable(mutableListOf(1, 2, 2, 3, 3, 3, 7, 8, 9, 10))
            .take(2)
            .repeat(3)
            .distinct()
            .subscribe({println(it)})


    Observable.fromIterable(list)
            .distinctUntilChanged()
            .subscribe{ println(it) }

//    list.add(11)
//    list.add(12)
//    list.add(13)
}