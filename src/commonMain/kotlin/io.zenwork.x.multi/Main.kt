package io.zenwork.x.multi

import kotlin.js.JsExport
import kotlin.js.JsName


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

@JsExport
@JsName("Result")
class Result(
    @JsName("name")
    val name: String = "",
    val status: Int = 200,
    val details: String = "",
    val message: String = "OK",
    val originalValue: String? = null
)


@JsExport
class Validation {
    fun validate(name: String, value: String): Result {
        return Result(name = name, originalValue = value, details = "nothing to do")
    }
}
