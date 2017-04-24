package compiler.ast

import compiler.CompilerTree

/**
 * Created by kseniya on 24/04/2017.
 */
class ArithmeticBinaryExpression(
        val left: Expression, 
        val right: Expression, 
        val operation: String
) : Expression() {
    override fun interpretate(symbolTable: Map<String, Int>): Int {
        val leftResult = left.interpretate(symbolTable)
        val rightResult = right.interpretate(symbolTable)
        return when (operation) {
            "+" -> leftResult + rightResult
            "-" -> leftResult - rightResult
            "*" -> leftResult * rightResult
            "/" -> leftResult / rightResult
            "%" -> leftResult % rightResult
            else -> throw IllegalStateException("unknown binary operation")
        }
    }
}