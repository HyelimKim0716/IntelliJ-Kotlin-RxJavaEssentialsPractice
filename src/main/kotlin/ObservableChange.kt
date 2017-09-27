import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function
import org.reactivestreams.Publisher
import java.text.SimpleDateFormat
import java.util.*

fun main(args: Array<String>) {
    val list : MutableList<String> = mutableListOf("Hayley", "NjStyle", "Dongle", "CliCli")
//    map(list)
//    flatMap(list)
//    concatMap(list)
//    flatMapIterable(list)
//    switchMapIterable(list)
//    scan(list)
//    groupBy(list)
//    buffer(list)
//    window(list)
    case()
}

fun map(list: MutableList<String>) {
    Observable.fromIterable(list)
            .map { it.toLowerCase() }
            .subscribe(System.out::println)

    Observable.fromIterable(list)
            .map(Function<String, Boolean> {
                t -> t.toUpperCase().startsWith("H")
            })
            .subscribe({
                System.out.println("next = $it")
            }, {
                System.out.println("error = ${it.message}")
            }, {
                System.out.println("completed")
            })
}

fun flatMap(list: MutableList<String>) {
    Flowable.fromIterable(list)
            .flatMap(Function<String, Publisher<Any>> {
                one ->
                System.out.println(one)
                Flowable.fromIterable(mutableListOf("Hayley", "Hyelim"))
            }).subscribe(System.out::println)

}

fun concatMap(list: MutableList<String>) {
    Observable.fromIterable(list)
            .concatMap {
                System.out.println(it)
                Observable.fromIterable(mutableListOf("a", "b"))
            }.subscribe(System.out::println)
}

fun flatMapIterable(list: MutableList<String>) {
    Observable.fromIterable(list)
            .flatMapIterable { mutableListOf(1, 2, 3) }
            .subscribe({
                System.out.println("next = $it")
            }, {
                System.out.println("error = ${it.message}")
            }, {
                System.out.println("completed")
            })
}

fun switchMapIterable(list: MutableList<String>) {
    Observable.fromIterable(list)
            .switchMap { Observable.fromIterable(mutableListOf<Int>(1, 2, 3, 4, 5)) }
            .subscribe({
                System.out.println("next = $it")
            }, {
                System.out.println("error = ${it.message}")
            }, {
                System.out.println("completed")
            })
}

fun scan(list: MutableList<String>) {
    Observable.just(1, 2, 3, 4, 5)
            .scan { t1: Int, t2: Int -> t1 + t2 }
            .subscribe({
                System.out.println("next = $it")
            }, {
                System.out.println("error = ${it.message}")
            }, {
                System.out.println("completed")
            })

    Observable.fromIterable(list)
            .scan { t1: String, t2: String ->
                if (t1.length > t2.length)
                    t1.toUpperCase()
                else
                    t2.toLowerCase()
            }
            .subscribe({
                System.out.println("next = $it")
            }, {
                System.out.println("error = ${it.message}")
            }, {
                System.out.println("completed")
            })
}

fun groupBy(list: MutableList<String>) {
    val groupedItems = Observable.fromIterable(list)
            .groupBy {
                val date = SimpleDateFormat("HH:mm:ss").format(Date())
                it + date
            }

    Observable.concat(groupedItems)
            .subscribe({
                System.out.println("next = $it")
            }, {
                System.out.println("error = ${it.message}")
            }, {
                System.out.println("completed")
            })
}

fun buffer(list: MutableList<String>) {
    Observable.fromIterable(list)
            .buffer(2)
            .subscribe({
                System.out.println("next = $it")
            }, {
                System.out.println("error = ${it.message}")
            }, {
                System.out.println("completed")
            })

    Observable.fromIterable(list)
            .buffer(2, 3)
            .subscribe({
                System.out.println("next = $it")
            }, {
                System.out.println("error = ${it.message}")
            }, {
                System.out.println("completed")
            })
}

fun window(list: MutableList<String>) {
    Observable.fromIterable(list)
            .window(2)
            .subscribe({
                it.map { it.toLowerCase() }
                        .subscribe(System.out::println)
//                System.out.println("next = ${it.map { it.toLowerCase() }}")
            }, {
                System.out.println("error = ${it.message}")
            }, {
                System.out.println("completed")
            })

    Observable.fromIterable(list)
            .window(2, 3)
            .subscribe({
                it.filter { it.contains("N") }
                        .subscribe(System.out::println)
//                System.out.println("next = ${it.filter { it.contains("N") }}")
            }, {
                System.out.println("error = ${it.message}")
            }, {
                System.out.println("completed")
            })
}

open class Test1(val name: String, val email: String)
class Test2(val id:String, val password: String) : Test1("Test1", "Test1@gmail.com")

fun case() {
    val list: MutableList<Test1> = mutableListOf(Test1("Hyelim", "Hayley"), Test1("NjStyle", "njStyle"))
    Observable.fromIterable(list)
            .cast(Test2::class.java)
            .subscribe(System.out::println)
}