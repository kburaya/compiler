package compiler.ast

/**
 * Created by kseniya on 24/04/2017.
 */
class StatementExpression(val expression: Expression) : Statement() {
    override fun interpretate(symbolTable: MutableMap<String, Int>) {
        expression.interpretate(symbolTable)
    }
}