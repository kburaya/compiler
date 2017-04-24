package compiler.ast

import compiler.CompilerTree

/**
 * Created by kseniya on 24/04/2017.
 */
abstract class Expression : CompilerTree() {
    abstract fun interpretate(symbolTable: Map<String, Int>): Int
}