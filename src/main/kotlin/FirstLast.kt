import io.reactivex.Observable

fun main(args: Array<String>) {
    Observable.fromArray(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
            .firstElement()
            .subscribe({
                item -> System.out.println(item)
            })
}