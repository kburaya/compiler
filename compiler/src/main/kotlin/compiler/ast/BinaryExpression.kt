
package compiler.ast

import compiler.stack.StackOperation
import compiler.toBoolean
import compiler.toInt

/**
 * Created by kseniya on 28/04/2017.
 */
class BinaryExpression(
        val left: Expression,
        val right: Expression,
        val operation: String
): Expression() {
    override fun generateStackCode(stackOperations: MutableList<StackOperation>) {
        left.generateStackCode(stackOperations)
        right.generateStackCode(stackOperations)
        stackOperations += compiler.stack.BinaryExpression(operation)

    }

    override fun interpretate(symbolTable: Map<String, Int>): Int {
        val leftResult = left.interpretate(symbolTable)
        val rightResult = right.interpretate(symbolTable)
        return when (operation) {
            "||", "!!" -> (leftResult.toBoolean() || rightResult.toBoolean()).toInt()
            "&&" -> (leftResult.toBoolean() && rightResult.toBoolean()).toInt()
            "<=" -> (leftResult <= rightResult).toInt()
            "<" -> (leftResult < rightResult).toInt()
            ">=" -> (leftResult >= rightResult).toInt()
            "==" -> (leftResult == rightResult).toInt()
            "!=" -> (leftResult != rightResult).toInt()
            ">" -> (leftResult > rightResult).toInt()
            "+" -> leftResult + rightResult
            "-" -> leftResult - rightResult
            "*" -> leftResult * rightResult
            "/" -> leftResult / rightResult
            "%" -> leftResult % rightResult
            else -> throw IllegalStateException("unknown binary operation $operation")
        }
    }
}