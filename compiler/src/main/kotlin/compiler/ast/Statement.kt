package compiler.ast

import compiler.CompilerTree

/**
 * Created by kseniya on 24/04/2017.
 */
abstract class Statement : CompilerTree() {
    abstract fun interpretate(symbolTable: MutableMap<String, Int>)
}