package compiler.ast

/**
 * Created by kseniya on 24/04/2017.
 */
class Write(val expression: Expression) : Statement() {
    override fun interpretate(symbolTable: MutableMap<String, Int>) {
        val expressionResult = expression.interpretate(symbolTable)
        println(expressionResult)
    }
}