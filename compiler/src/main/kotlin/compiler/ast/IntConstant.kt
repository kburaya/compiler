package compiler.ast

import compiler.stack.Push
import compiler.stack.StackOperation

/**
 * Created by kseniya on 24/04/2017.
 */
class IntConstant(val value: Int) : Expression() {
    override fun interpretate(symbolTable: Map<String, Int>): Int = value
    override fun generateStackCode(stackOperations: MutableList<StackOperation>) {
        stackOperations.add(Push(value))
    }
}