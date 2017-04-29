package compiler.stack

import compiler.toBoolean
import compiler.toInt
import java.util.*

/**
 * Created by kseniya on 29/04/2017.
 */
sealed class StackOperation {
    abstract fun interpretate(stackOperations: ArrayList<Int>, symbolTable: MutableMap<String, Int>)
}

class Push(val value: Int): StackOperation() {
    override fun interpretate(stackOperations: ArrayList<Int>, symbolTable: MutableMap<String, Int>) {
        stackOperations += value
    }
}

object Pop: StackOperation() {
    override fun interpretate(stackOperations: ArrayList<Int>, symbolTable: MutableMap<String, Int>) {
        stackOperations.pop()
    }

}

class Load(val name: String): StackOperation() {
    override fun interpretate(stackOperations: ArrayList<Int>, symbolTable: MutableMap<String, Int>) {
        stackOperations += symbolTable[name] ?: throw IllegalStateException("unknown variable $name")
    }

}

class Store(val name: String): StackOperation() {
    override fun interpretate(stackOperations: ArrayList<Int>, symbolTable: MutableMap<String, Int>) {
        symbolTable[name] = stackOperations.pop()
    }

}

class BinaryExpression(val operation: String): StackOperation() {
    override fun interpretate(stackOperations: ArrayList<Int>, symbolTable: MutableMap<String, Int>) {
        val rightResult = stackOperations.pop()
        val leftResult = stackOperations.pop()
        val result = when (operation) {
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
        stackOperations += result
    }

}

object Read: StackOperation() {

    private val scanner = Scanner(System.`in`)

    override fun interpretate(stackOperations: ArrayList<Int>, symbolTable: MutableMap<String, Int>) {
        print("> ")
        stackOperations += scanner.nextInt()
    }

}

object Write: StackOperation() {
    override fun interpretate(stackOperations: ArrayList<Int>, symbolTable: MutableMap<String, Int>) {
        println(stackOperations.pop())
    }
}

private fun <T> ArrayList<T>.pop(): T = removeAt(lastIndex)