package compiler.ast

import compiler.stack.Pop
import compiler.stack.StackOperation

/**
 * Created by kseniya on 24/04/2017.
 */
class StatementExpression(val expression: Expression) : Statement() {
    override fun generateStackCode(stackOperations: MutableList<StackOperation>) {
        expression.generateStackCode(stackOperations)
        stackOperations += Pop
    }

    override fun interpretate(symbolTable: MutableMap<String, Int>) {
        expression.interpretate(symbolTable)
    }
}