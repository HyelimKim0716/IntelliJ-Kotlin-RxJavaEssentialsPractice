import io.reactivex.Observable

fun main(args: Array<String>) {
    Observable.fromIterable(mutableListOf(1, 2, 2, 3, 3, 3, 7, 8, 9, 10))
            .elementAt(2)
            .subscribe({
                item -> System.out.println(item)
            })

    Observable.range(1, 10)
            .elementAt(2)
            .subscribe({
                item -> System.out.println(item)
            }
            )

}