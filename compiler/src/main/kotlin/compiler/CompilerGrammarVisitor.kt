package compiler

/**
 * Created by kseniya on 18/04/2017.
 */
class CompilerGrammarVisitor : GrammarBaseVisitor<VisitorResult>() {

    override fun visitExpression(ctx: GrammarParser.ExpressionContext): VisitorResult {
        if (ctx.basic() != null) {
            return visitBasic(ctx.basic())
        }
        if (ctx.op != null) {
            val leftResult = visitExpression(ctx.left) as IntResult
            val rightResult = visitExpression(ctx.right) as IntResult
            val value = when (ctx.op.text) {
                "+" -> leftResult.value + rightResult.value
                "-" -> leftResult.value - rightResult.value
                "*" -> leftResult.value * rightResult.value
                "/" -> leftResult.value / rightResult.value
                "%" -> leftResult.value % rightResult.value
                else -> throw IllegalStateException("unknown binary operation")
            }
            println("visitExpression: $value")
            return IntResult(value)
        }
        return NoResult()
    }

    override fun visitBasic(ctx: GrammarParser.BasicContext): VisitorResult {
        return when {
            ctx.expression() != null -> visitExpression(ctx.expression())
            ctx.intLiteral() != null -> visitIntLiteral(ctx.intLiteral())
            ctx.variable() != null -> visitVariable(ctx.variable())
            else -> throw IllegalStateException()
        }
    }

    override fun visitIntLiteral(ctx: GrammarParser.IntLiteralContext): IntResult {
        val value = ctx.IntLiteral().text.toInt()
        println("visitIntLiteral: $value")
        return IntResult(value)
    }
//
//    override fun visitVariable(ctx: GrammarParser.VariableContext): VisitorResult {
//        val varName = ctx.Identifier()
//
//    }
//
//    override fun visitGlobalDeclaration(ctx: GrammarParser.GlobalDeclarationContext): VisitorResult {
//        val result = visitExpression(ctx.expression())
//
//    }
}