package io.zenwork.x.multi

import kotlin.js.JsExport

@JsExport
interface Calc {
    fun add(a: Int, b: Int): Calculator
    fun equalz(): Int
}

@JsExport
class Calculator : Calc {
    private var result: Int = 0

    override fun add(a: Int, b: Int): Calculator {
        result = a + b
        return this
    }

    override fun equalz(): Int {
        val eq = result
        result = 0
        println("equals: $eq")
        return eq
    }

}
