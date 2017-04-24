package compiler.ast

import java.util.*

/**
 * Created by kseniya on 24/04/2017.
 */

class Read : Expression() {
    override fun interpretate(symbolTable: Map<String, Int>): Int {
        val scanner = Scanner(System.`in`)
        return scanner.nextInt()
    }
}