package compiler.ast

import compiler.CompilerTree

/**
 * Created by kseniya on 24/04/2017.
 */
class IntConstant(val value: Int) : Expression() {
    override fun interpretate(symbolTable: Map<String, Int>): Int = value
}