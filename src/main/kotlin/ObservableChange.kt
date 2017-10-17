import io.reactivex.Flowable
import io.reactivex.Observable

fun main(args: Array<String>) {
    val list : MutableList<String> = mutableListOf("Hayley", "NjStyle", "Dongle", "CliCli")
//    map(list)
//    flatMap(list)     // 순서 상관 없이 (인터리빙 허용)
//    concatMap(list)     // 순서대로 (인터리빙 허용하지 않음)
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
            .map({
                it.toUpperCase().startsWith("H")
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
            .flatMap({
                one -> /*System.out.println(one)*/
                Flowable.fromIterable(mutableListOf("a", "b")).map {
                    it.toUpperCase() + one
                }
            }).subscribe(System.out::println)


    list.forEach {
        println(it)
    }
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
            .switchMap { one -> Observable.fromIterable(mutableListOf<Int>(1, 2, 3, 4, 5)).map {
                it.toString() + one
            } }
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
    val groupedItems = Observable.fromIterable(listOf(1, 2, 3, 4))
            .groupBy { it %2 == 0 }
            .subscribe({
                System.out.println("next = $it")
            }, {
                System.out.println("error = ${it.message}")
            }, {
                System.out.println("completed")
            })

//    Observable.concat(groupedItems)
//            .subscribe({
//                System.out.println("next = $it")
//            }, {
//                System.out.println("error = ${it.message}")
//            }, {
//                System.out.println("completed")
//            })
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
            .buffer(2, 1)
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
                println("onNext=======")
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
                println("onNext=======")
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
            .cast(String::class.java)
            .subscribe(System.out::println)
}