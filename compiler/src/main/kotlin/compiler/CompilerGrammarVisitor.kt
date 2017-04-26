package compiler

import compiler.ast.*

/**
 * Created by kseniya on 18/04/2017.
 */
private val ARITHMETIC_OPERATION = listOf("+", "-", "*", "/", "%")

class CompilerGrammarVisitor : GrammarBaseVisitor<CompilerTree>() {

    override fun visitExpression(ctx: GrammarParser.ExpressionContext): Expression {
        if (ctx.basic() != null) {
            return visitBasic(ctx.basic())
        }
        if (ctx.op != null) {
            val leftResult = visitExpression(ctx.left)
            val rightResult = visitExpression(ctx.right)
            val operation = ctx.op.text
            return when {
                operation in ARITHMETIC_OPERATION -> ArithmeticBinaryExpression(leftResult, rightResult, operation)
                else -> throw IllegalStateException("unknown binary operation")
            }
        }
        throw IllegalStateException()
    }

    override fun visitBasic(ctx: GrammarParser.BasicContext): Expression {
        return when {
            ctx.expression() != null -> visitExpression(ctx.expression())
            ctx.intLiteral() != null -> visitIntLiteral(ctx.intLiteral())
            ctx.read() != null -> visitRead(ctx.read())
            ctx.variable() != null -> visitVariable(ctx.variable())
            else -> throw IllegalStateException()
        }
    }

    override fun visitRead(ctx: GrammarParser.ReadContext): Read = Read()

    override fun visitIntLiteral(ctx: GrammarParser.IntLiteralContext): IntConstant {
        return IntConstant(ctx.IntLiteral().text.toInt())
    }

    override fun visitVariable(ctx: GrammarParser.VariableContext): Variable = Variable(ctx.Identifier().text)

    override fun visitGlobalDeclaration(ctx: GrammarParser.GlobalDeclarationContext): Declaration {
        val result = visitExpression(ctx.expression())
        val varName = ctx.Identifier().text
        return Declaration(varName, result)
    }

    override fun visitStatement(ctx: GrammarParser.StatementContext): Statement {
        return when {
            ctx.globalDeclaration() != null -> visitGlobalDeclaration(ctx.globalDeclaration())
            ctx.write() != null -> visitWrite(ctx.write())
            ctx.expression() != null -> StatementExpression(visitExpression(ctx.expression()))
            ctx.first != null -> SequenceStatement(visitStatement(ctx.first), visitStatement(ctx.second))
            ctx.skip() != null -> visitSkip(ctx.skip())
            else -> throw IllegalStateException()
        }
    }

    override fun visitWrite(ctx: GrammarParser.WriteContext): Statement {
        val result = visitExpression(ctx.expression())
        return Write(result)
    }

    override fun visitSkip(ctx: GrammarParser.SkipContext): Statement = Skip()

    override fun visitProgram(ctx: GrammarParser.ProgramContext): Program {
        return Program(visitStatement(ctx.statement()))
    }
}