package compiler.ast

import compiler.stack.Load
import compiler.stack.Push
import compiler.stack.StackOperation

/**
 * Created by kseniya on 24/04/2017.
 */

class Variable(val name: String) : Expression() {
    override fun generateStackCode(stackOperations: MutableList<StackOperation>) {
        stackOperations.add(Load(name))
    }

    override fun interpretate(symbolTable: Map<String, Int>): Int {
        return symbolTable[name] ?: throw IllegalStateException("unknown variable $name")
    }
}
