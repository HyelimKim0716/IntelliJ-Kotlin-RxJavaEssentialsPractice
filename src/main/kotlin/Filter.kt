import io.reactivex.Observable
import io.reactivex.functions.Predicate

fun main(args: Array<String>) {
    testFilter()
}
fun testFilter() {
    val list: ArrayList<String> = arrayListOf("hyelim", "nsStyle", "dongle", "clicli")

    Observable.fromArray(list)
            .subscribe { one ->

                System.out.println(one)
            }
}