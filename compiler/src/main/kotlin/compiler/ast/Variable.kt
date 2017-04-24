package compiler.ast

import compiler.CompilerTree

/**
 * Created by kseniya on 24/04/2017.
 */

class Variable(val name: String) : Expression() {
    override fun interpretate(symbolTable: Map<String, Int>): Int {
        return symbolTable[name] ?: throw IllegalStateException("unknown variable $name")
    }
}
