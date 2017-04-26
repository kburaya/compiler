@file:JvmName("Main")
package compiler
import compiler.ast.Program
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

/**
 * Created by kseniya on 18/04/2017.
 */


fun main(args: Array<String>) {
    when (args[0]) {
        "-i" -> try {
            val programSource = readFile(args[1])
            interpretateInput(programSource)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun readFile(input: String): String = File(input).readText()

fun interpretateInput(programSource: String) {
    val grammarLexer = GrammarLexer(ANTLRInputStream(programSource))
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
