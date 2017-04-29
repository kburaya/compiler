package compiler.ast

import compiler.stack.StackOperation

/**
 * Created by kseniya on 24/04/2017.
 */
class SequenceStatement(val first: Statement, val second: Statement) : Statement() {
    override fun generateStackCode(stackOperations: MutableList<StackOperation>) {
        first.generateStackCode(stackOperations)
        second.generateStackCode(stackOperations)
    }

    override fun interpretate(symbolTable: MutableMap<String, Int>) {
        first.interpretate(symbolTable)
        second.interpretate(symbolTable)
    }
}