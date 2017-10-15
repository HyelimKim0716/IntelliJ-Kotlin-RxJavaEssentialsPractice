import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val list : ArrayList<String> = arrayListOf("Hayley", "NjStyle", "Dongle", "CliCli")
    val numberList = arrayListOf<Int>(1, 2, 3, 4)
    merge(list, numberList)

}

fun merge(stringList: ArrayList<String>, numberList: ArrayList<Int>) {
    val observableList: Observable<String> = Observable.fromIterable(stringList)
    val observableNumberList: Observable<Int> = Observable.fromIterable(numberList)
    val mergedObservable: Observable<Any> = Observable.merge(observableList, observableNumberList)

    stringList.add("A")
    stringList.add("B")
    numberList.add(100)
    numberList.add(200)
    stringList.add("C")
    mergedObservable.subscribe({
        println(it)
    }, {
        println("Error : ${it.message}")
    }, {
        println("completed!")
    })
}

fun zip(stringList: ArrayList<String>, numberList: ArrayList<Int>) {
    val tictoc: Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)
//    Observable.zip(stringList, tictoc, {t1: String, t2: Int -> "$t1 $t2" })
}

fun zipFunction(name: String, num: Int) : String = "$name $num"