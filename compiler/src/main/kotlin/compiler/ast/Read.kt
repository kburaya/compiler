package compiler.ast

import java.util.*

/**
 * Created by kseniya on 24/04/2017.
 */

class Read : Expression() {
    override fun interpretate(symbolTable: Map<String, Int>): Int {
        print("> ")
        return SCANNER.nextInt()
    }

    companion object {
        private val SCANNER = Scanner(System.`in`)
    }
}