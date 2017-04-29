package compiler

/**
 * Created by kseniya on 29/04/2017.
 */
fun Boolean.toInt(): Int = if (this) 1 else 0
fun Int.toBoolean(): Boolean = this != 0