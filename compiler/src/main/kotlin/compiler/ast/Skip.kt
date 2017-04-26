package compiler.ast

/**
 * Created by kseniya on 26/04/2017.
 */
class Skip : Statement() {
    override fun interpretate(symbolTable: MutableMap<String, Int>) {}
}