import io.reactivex.Observable

fun main(args: Array<String>) {
    testFilter()
}
fun testFilter() {
    Observable.fromIterable(mutableListOf("hyelim", "njStyle", "dongle", "clicli"))
            .filter { it.length > 6 }
            .subscribe (System.out::println)
}