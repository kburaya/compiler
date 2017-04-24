package compiler
import compiler.ast.Program
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream

/**
 * Created by kseniya on 18/04/2017.
 */

fun main(args: Array<String>) {
    // get lexer
    val grammarLexer = GrammarLexer(ANTLRInputStream("""
        a := read();
        b := read();
        write(2 * a + b)
    """))
    // get list of tokens
    val tokenStream = CommonTokenStream(grammarLexer)
    // pass tokens to parser
    val grammarParser = GrammarParser(tokenStream)
    // specify entry point
    val programContext = grammarParser.program()
    // walk and attach the listener
    val visitor = CompilerGrammarVisitor()
    val program = visitor.visitProgram(programContext)
    program.interpretate()
}
