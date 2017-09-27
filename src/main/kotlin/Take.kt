import io.reactivex.Flowable
import io.reactivex.Observable

fun main(args: Array<String>) {
    val list: ArrayList<String> = arrayListOf("hyelim", "nsStyle", "dongle", "clicli")
    Observable.fromIterable(mutableListOf(list))
            .take(2)
            .subscribe( { item -> System.out.println(item) },
                    { t -> System.out.println("Error = ${t.message}") })

    Observable.fromIterable(mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
            .takeLast(2)
            .subscribe(System.out::println)

    Flowable.just(list)
            .take(2)
            .subscribe(System.out::println)
}