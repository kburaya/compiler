package compiler.ast

import compiler.stack.StackOperation
import compiler.stack.Store

/**
 * Created by kseniya on 23/04/2017.
 */
class Declaration(val name: String, val expression: Expression) : Statement() {
    override fun generateStackCode(stackOperations: MutableList<StackOperation>) {
        expression.generateStackCode(stackOperations)
        stackOperations += Store(name)
    }

    override fun interpretate(symbolTable: MutableMap<String, Int>) {
        val expressionResult = expression.interpretate(symbolTable)
        symbolTable[name] = expressionResult
    }
}