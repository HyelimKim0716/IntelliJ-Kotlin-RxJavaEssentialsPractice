import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Single
import io.reactivex.functions.Function
import java.text.SimpleDateFormat
import java.util.*
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
//    switch()


    Maybe.fromCallable {
        val nullableStr: String? = ""
        nullableStr
    }.flatMapSingle {
        Single.just("Single")
    }.subscribe(
            ::println, // onSuccess
            { it.printStackTrace() } // onError
    )


    Observable.fromIterable(numList)
            .doOnNext { println(it) }
            .all { it % 2 == 0 }
            .doOnSuccess { println(it) }
            .subscribe { it -> println(it) }

}

fun merge() {
    // Merge 하다 중간에 에러가 날 경우, stop -> error message
    Observable.merge(Observable.fromIterable(nameList), Observable.fromIterable(numList))
            .subscribe({
                println(it)
            }, {
                println("Error : " + it.message)
            }, {
                println("Completed")
            })

    // Merge 중 에러가 나더라도 Merge 수행 후 error message
    Observable.mergeDelayError(Observable.fromIterable(nameList), Observable.fromIterable(numList))
            .subscribe({
                println(it)
            }, {
                println("Error : " + it.message)
            }, {
                println("Completed")
            })
}

fun zip() {
    Observable.zip(Observable.fromIterable(nameList), Observable.fromIterable(numList),
            io.reactivex.functions.BiFunction<String, Int, String> { t1, t2 -> "$t1 $t2" }
//            object : BiFunction<String, Int, String>, io.reactivex.functions.BiFunction<String, Int, Any> {
//        override fun apply(t1: String, t2: Int): String {
//            return "$t1 $t2"
//        }
    ).subscribe({
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
    val twoSec = Observable.interval(2, TimeUnit.SECONDS)

    oneSec.join(
            twoSec,
            Function<Long, ObservableSource<Long>> {
                printlnWithTime("left func ${it + 1}")
                Observable.timer(5, TimeUnit.SECONDS)
            },
            Function<Long, ObservableSource<Long>> {
                printlnWithTime("right func ${it + 1}")
                Observable.timer(2, TimeUnit.SECONDS)
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

//    val test = Observable.just("1 ", "2 ")
    val test = Observable.just(1L, 2L)
    twoSec.startWith(test)
//    Observable.fromIterable(nameList).startWith(test)
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

fun printlnWithTime(msg: Any?) {
    val sdf = SimpleDateFormat("hh:mm:ss.SSS")
    println(sdf.format(Date()).toString() + "   " +  msg)
}