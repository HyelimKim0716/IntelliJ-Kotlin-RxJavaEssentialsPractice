import io.reactivex.Observable
import io.reactivex.functions.Predicate

fun main(args: Array<String>) {
    testFilter()
}
fun testFilter() {
    Observable.fromArray(mutableListOf("hyelim", "nsStyle", "dongle", "clicli"))
            .subscribe { one -> System.out.println(one) }
}