package compiler.ast

import compiler.stack.StackOperation

/**
 * Created by kseniya on 24/04/2017.
 */
class Program(val statement: Statement) : CompilerTree() {
    fun interpretate() {
        val symbolTable = HashMap<String, Int>()
        statement.interpretate(symbolTable)
    }

    fun generateStackCode(): List<StackOperation> {
        val stackOperations = ArrayList<StackOperation>()
        statement.generateStackCode(stackOperations)
        return stackOperations
    }
}