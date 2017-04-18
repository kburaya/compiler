package compiler

/**
 * Created by kseniya on 18/04/2017.
 */
class CompilerGrammarListener : GrammarBaseListener() {
    override fun enterProgram(ctx: GrammarParser.ProgramContext) {
        println(ctx)
    }


}
