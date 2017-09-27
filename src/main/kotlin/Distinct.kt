import io.reactivex.Observable

fun main(args: Array<String>) {
    Observable.fromArray(arrayOf(1, 2, 2, 3, 3, 3, 7, 8, 9, 10))
            .take(2)
            .repeat(3)
            .distinct()
            .subscribe({item -> System.out.println(item.size)})

    Observable.fromArray(arrayOf(1, 2, 2, 3, 3,  5, 6, 7, 8, 9, 10))
            .distinctUntilChanged()
}