@file:JvmName("Main")
package compiler
import compiler.ast.Program
import compiler.stack.StackMachine
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File

/**
 * Created by kseniya on 18/04/2017.
 */


fun main(args: Array<String>) {
    when (args[0]) {
        "-i" -> try {
            val programSource = readFile(args[1])
            interpretate(programSource)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        "-s" -> try {
            val programSource = readFile(args[1])
            runStackMachine(programSource)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun readFile(input: String): String = File(input).readText()

fun interpretate(programSource: String) {
    val program = parseProgram(programSource)
    program.interpretate()
}

fun runStackMachine(programSource: String) {
    val program = parseProgram(programSource)
    val stackOperations = program.generateStackCode()
    val stackMachine = StackMachine()
    stackMachine.intepretate(stackOperations)
}

fun parseProgram(programSource: String): Program {
    val grammarLexer = GrammarLexer(ANTLRInputStream(programSource))
    // get list of tokens
    val tokenStream = CommonTokenStream(grammarLexer)
    // pass tokens to parser
    val grammarParser = GrammarParser(tokenStream)
    // specify entry point
    val programContext = grammarParser.program()
    // walk and attach the listener
    val visitor = CompilerGrammarVisitor()
    return visitor.visitProgram(programContext)
}