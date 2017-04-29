package compiler.ast

import compiler.stack.StackOperation

/**
 * Created by kseniya on 24/04/2017.
 */
abstract class Expression : CompilerTree() {
    abstract fun interpretate(symbolTable: Map<String, Int>): Int
    abstract fun generateStackCode(stackOperations: MutableList<StackOperation>)
}