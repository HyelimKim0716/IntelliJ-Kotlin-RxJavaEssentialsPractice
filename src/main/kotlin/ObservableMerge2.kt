import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function
import java.util.concurrent.TimeUnit
import java.util.function.BiFunction

var nameList = listOf("Hayley", "NjStyle", "Dongle", "CliilC")
var numList = listOf(1, 2, 3, 4, 5)
fun main(args: Array<String>) {
//    merge()
//    zip()
//    join()
//    combineLatest()
//    andThenWhen()
    switch()
}

fun merge() {
    Observable.merge(Observable.fromIterable(nameList), Observable.fromIterable(numList))
            .subscribe({
                println(it)
            }, {
                println("Error : " + it.message)
            }, {
                println("Completed")
            })
}

fun zip() {
    Observable.zip(Observable.fromIterable(nameList), Observable.fromIterable(numList), object : BiFunction<String, Int, String>, io.reactivex.functions.BiFunction<String, Int, Any> {
        override fun apply(t1: String, t2: Int): String {
            return "$t1 $t2"
        }
    }).subscribe({
        println(it)
    }, {
        println("Error : " + it.message)
    }, {
        println("Completed")
    })
}

fun join() {
    println("start")
    val oneSec = Observable.interval(1, TimeUnit.SECONDS)
//            .subscribe({
//                println("on next = " + it)
//            }, {
//                println("Error : " + it.message)
//            }, {
//                println("Completed")
//            }).run { println("running!! ")}



    val twoSec = Observable.interval(2, TimeUnit.SECONDS)

    oneSec.join(
            twoSec,
            Function<Long, ObservableSource<Long>> {
                println("left func ${it + 1}")
                Observable.timer(5, TimeUnit.SECONDS)
            },
            Function<Long, ObservableSource<Long>> {
                println("right func ${it + 1}")
                Observable.timer(0, TimeUnit.SECONDS)
            },
            io.reactivex.functions.BiFunction<Long, Long, String> { left, right -> "${left + 1} ${right + 1}" }
    ).subscribe({
        println(it)
    }, {
        println("Error : " + it.message)
    }, {
        println("Completed")
    })

    Thread.sleep(20000)
}

fun combineLatest() {
    val observableName = Observable.interval(500, TimeUnit.MILLISECONDS)
            .map { nameList[it.toInt()] }
    val tictoc = Observable.interval(600, TimeUnit.MILLISECONDS)

    Observable.combineLatest(observableName, tictoc, io.reactivex.functions.BiFunction<String, Long, String> {
        t1, t2 -> "result = $t1 $t2"
    }).subscribe({
        println("onNext = $it")
    }, {
        println("Error : " + it.message)
    }, {
        println("Completed")
    })

    Thread.sleep(2000)
}

fun andThenWhen() {

}

fun switch() {
    val oneSec = Observable.interval(1, TimeUnit.SECONDS).takeUntil { time -> time < 5000 }
    val twoSec = Observable.interval(2, TimeUnit.SECONDS)
//    Observable.fromIterable(nameList).startWith("123")
    twoSec.startWith(oneSec)
            .subscribe({
                println("onNext = $it")
            }, {
                println("Error : " + it.message)
            }, {
                println("Completed")
            })

    Thread.sleep(5000)

    Thread.sleep(10000)
}