import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val list : MutableList<String> = mutableListOf("hayley", "nsStyle", "dongle", "clicli")
//    first(list)
//    last(list)
//    elementAt(list)
    sample(list)
    timeout(list)
    debounce(list)
}

fun first(list: MutableList<String>) {
    Observable.fromIterable(list)
            .firstElement()
            .subscribe{
                System.out.println(it)
            }
}

fun last(list: MutableList<String>) {
    Observable.fromIterable(list)
            .lastElement()
//            .last("hayley")
            .subscribe({
                System.out.println(it)
            }, {
                System.out.println(it.message)
            })
}

fun elementAt(list: MutableList<String>) {
    Observable.fromIterable(list)
//            .elementAtOrError(5)
//            .elementAt(5)
            .elementAt(2)
            .subscribe({
                System.out.println(it)
            }, {
                System.out.println("ERROR : ${it.message}")
            })
}

fun sample(list: MutableList<String>) {
    Observable.interval(3, TimeUnit.SECONDS)
            .subscribe({
                list.add("Add " + it)
                System.out.println("interval list size = ${list.size}")
            })

    Observable.fromIterable(list)
            .sample(1, TimeUnit.SECONDS)
            .subscribe({
                System.out.println(it)
            }, {

            }, {
                System.out.println("sample completed")
            })
}

fun timeout(list: MutableList<String>) {
    Observable.fromIterable(list)
            .timeout(3, TimeUnit.SECONDS)
            .subscribe({
                System.out.println("next = $it")
            }, {
                System.out.println("error = ${it.message}")
            }, {
                System.out.println("completed")
            })
}

fun debounce(list: MutableList<String>) {
    Observable.fromIterable(list)
            .debounce(3, TimeUnit.SECONDS)
            .subscribe({
                System.out.println("next = $it")
            }, {
                System.out.println("error = ${it.message}")
            }, {
                System.out.println("completed")
            })
}