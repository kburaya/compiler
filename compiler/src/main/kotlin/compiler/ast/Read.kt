package compiler.ast

import compiler.stack.StackOperation
import java.util.*

/**
 * Created by kseniya on 24/04/2017.
 */

object Read : Expression() {

    private val scanner = Scanner(System.`in`)

    override fun generateStackCode(stackOperations: MutableList<StackOperation>) {
        stackOperations.add(compiler.stack.Read)
    }

    override fun interpretate(symbolTable: Map<String, Int>): Int {
        print("> ")
        return scanner.nextInt()
    }
}
