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
        println("equals: [$eq]")
        return eq
    }

}

@JsExport
@JsName("Result")
class Result(
    val rule: String = "",
    val status: Int = 200,
    val details: String = "",
    val message: String = "OK",
    val originalValue: Any? = null
)

@JsExport
@JsName("Rule")
abstract class Rule(val name: String) {
    abstract fun <T> rule(value: T): Result
}


@JsExport
class Validation(private val rules: Array<Rule> = arrayOf()) {

    fun validate(name: String, value: Any?): Result {
        when {
            name == "is/string" && value is CharSequence -> {
                return OK(name, value)
            }

            name == "is/null" && value == null -> {
                return OK(name, value)
            }

            name.startsWith("is/number") && value is Number -> {
                when {
                    name == "is/number/positive" && value.toDouble() < 1 -> {
                        return NOK(name, value)
                    }

                    name == "is/number/negative" && value.toDouble() > 1 -> {
                        return NOK(name, value)
                    }

                    else -> return OK(name, value)
                }


            }

            name.startsWith("is/x") && rules.any { rule -> rule.name == name } -> {
                println("hi")
                return rules.first { rule -> rule.name == name }.rule(value)
            }

            else -> return NOK(name, value)
        }

    }

}

@JsExport
fun OK(name: String, value: Any?) =
    Result(rule = name, originalValue = value, details = "nothing to do")

@JsExport
fun NOK(name: String, value: Any?): Result {
    return Result(
        rule = name,
        originalValue = value,
        details = "[$value] failed [${name.replace("/x", "").replace(Regex("[/_-]"), " ")}] assertion",
        status = 500,
        message = "NOK"
    )
}
