package compiler.ast

import compiler.CompilerTree

/**
 * Created by kseniya on 23/04/2017.
 */
class Declaration(val name: String, val expression: Expression) : Statement() {
    override fun interpretate(symbolTable: MutableMap<String, Int>) {
        val expressionResult = expression.interpretate(symbolTable)
        symbolTable[name] = expressionResult
    }
}