package patterns

class NumberMaster {

    companion object Parser {
        fun valueOf(number: String): Long {
            return number.toLong()
        }
    }

}