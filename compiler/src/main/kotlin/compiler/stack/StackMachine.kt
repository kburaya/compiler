package compiler.stack

/**
 * Created by kseniya on 29/04/2017.
 */
class StackMachine {
    fun intepretate(stackOperations: List<StackOperation>) {
        val stack = ArrayList<Int>()
        val symbolTable = HashMap<String, Int>()
        for (operation in stackOperations) {
            operation.interpretate(stack, symbolTable)
        }
    }
}