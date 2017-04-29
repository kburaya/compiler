package compiler.ast

import compiler.stack.StackOperation

/**
 * Created by kseniya on 24/04/2017.
 */
class Write(val expression: Expression) : Statement() {
    override fun generateStackCode(stackOperations: MutableList<StackOperation>) {
        expression.generateStackCode(stackOperations)
        stackOperations += compiler.stack.Write
    }

    override fun interpretate(symbolTable: MutableMap<String, Int>) {
        val expressionResult = expression.interpretate(symbolTable)
        println(expressionResult)
    }
}