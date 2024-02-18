fun task(): List<Boolean> {
    val isEven: Int.() -> Boolean = { this % 2 == 0 }
    val isOdd: Int.() -> Boolean = { this % 2 != 0 }

    return listOf(42.isOdd(), 239.isOdd(), 294823098.isEven())
}



import java.util.HashMap

fun <K, V> buildMutableMap(build: HashMap<K, V>.() -> Unit): Map<K, V> {
    val map = HashMap<K, V>()
    map.build()
    return map
}

fun usage(): Map<Int, String> {
    return buildMutableMap {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}



fun <T> T.myApply(f: T.() -> Unit): T {
    f()
    return this
}

fun createString(): String {
    return StringBuilder().myApply {
        append("Numbers: ")
        for (i in 1..10) {
            append(i)
        }
    }.toString()
}

fun createMap(): Map<Int, String> {
    return hashMapOf<Int, String>().myApply {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}



fun renderProductTable(): String {
    return html {
        table {
            tr(color = getTitleColor()) {
                td {
                    text("Product")
                }
                td {
                    text("Price")
                }
                td {
                    text("Popularity")
                }
            }
            val products = getProducts()
            for ((index, product) in products.withIndex()) {
                tr {
                    td(color = getCellColor(index, 0)) {
                        text(product.description)
                    }
                    td(color = getCellColor(index, 1)) {
                        text(product.price)
                    }
                    td(color = getCellColor(index, 2)) {
                        text(product.popularity)
                    }
                }
            }
        }
    }.toString()
}

fun getTitleColor() = "#b9c9fe"
fun getCellColor(index: Int, column: Int) = if ((index + column) % 2 == 0) "#dce4ff" else "#eff2ff"




import Answer.*

enum class Answer { a, b, c }

val answers = mapOf<Int, Answer?>(
        1 to c, 2 to b, 3 to b, 4 to c
)




open class Tag(val name: String) {
    protected val children = mutableListOf<Tag>()

    override fun toString() =
            "<$name>${children.joinToString("")}</$name>"
}

fun table(init: TABLE.() -> Unit): TABLE {
    val table = TABLE()
    table.init()
    return table
}

class TABLE : Tag("table") {
    fun tr(init: TR.() -> Unit) {
        val tr = TR()
        tr.init()
        children += tr
    }
}

class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) {
        children += TD().apply(init)
    }
}

class TD : Tag("td")

fun createTable() =
        table {
            tr {
                repeat(2) {
                    td {
                    }
                }
            }
        }

fun main() {
    println(createTable())
    //<table><tr><td></td><td></td></tr></table>
}









