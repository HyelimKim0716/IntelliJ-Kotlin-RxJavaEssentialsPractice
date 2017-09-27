import io.reactivex.Observable

fun main(args: Array<String>) {
    Observable.fromIterable(mutableListOf(1, 2, 2, 3, 3, 3, 7, 8, 9, 10))
            .take(2)
            .repeat(3)
            .distinct()
            .subscribe({item -> System.out.println(item)})

    Observable.fromIterable(mutableListOf(1, 2, 2, 3, 3, 3, 7, 8, 9, 10))
            .distinctUntilChanged()
}