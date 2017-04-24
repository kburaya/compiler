package compiler.ast

/**
 * Created by kseniya on 24/04/2017.
 */
class SequenceStatement(val first: Statement, val second: Statement) : Statement() {
    override fun interpretate(symbolTable: MutableMap<String, Int>) {
        first.interpretate(symbolTable)
        second.interpretate(symbolTable)
    }
}