import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

fun main(args: Array<String>) {
    val list: ArrayList<String> = arrayListOf("hyelim", "nsStyle", "dongle", "clicli")
    Observable.fromIterable(mutableListOf(list.toMutableList()))
            .skip(2)
            .subscribe( { item -> System.out.println(item) },
                    { t -> System.out.println("Error = ${t.message}") })

    Observable.fromArray(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
            .skipLast(2)
            .subscribe(System.out::println)

    Flowable.just(list)
            .take(2)
            .subscribe(System.out::println)
}