package compiler

/**
 * Created by kseniya on 18/04/2017.
 */
sealed class VisitorResult

data class IntResult(val value: Int) : VisitorResult()
class NoResult : VisitorResult() {
    override fun toString(): String = "No result"
}