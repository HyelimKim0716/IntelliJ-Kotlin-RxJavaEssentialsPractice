import io.reactivex.Flowable
import io.reactivex.Observable

fun main(args: Array<String>) {
    val list: ArrayList<String> = arrayListOf("hyelim", "njStyle", "dongle", "clicli")
    Observable.fromIterable(list)
            .take(2)
            .subscribe( { println(it) },
                    { println("Error = ${it.message}") },       // count < 0
                    { println("Completed")})

    Observable.fromIterable(mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
            .takeLast(2)
            .subscribe{ println(it) }

    Flowable.just(list)
            .take(2)
            .subscribe(System.out::println)
}