package compiler.ast

import compiler.stack.StackOperation

/**
 * Created by kseniya on 26/04/2017.
 */
class Skip : Statement() {
    override fun generateStackCode(stackOperations: MutableList<StackOperation>) {}

    override fun interpretate(symbolTable: MutableMap<String, Int>) {}

}