package uk.co.newagedev.rubiksart.model.cube

enum class Move {
    UP,
    UP_2,
    UP_PRIME,

    LEFT,
    LEFT_2,
    LEFT_PRIME,

    FRONT,
    FRONT_2,
    FRONT_PRIME,

    RIGHT,
    RIGHT_2,
    RIGHT_PRIME,

    BACK,
    BACK_2,
    BACK_PRIME,

    DOWN,
    DOWN_2,
    DOWN_PRIME;

    override fun toString(): String {
        return when (this) {
            UP -> "U"
            UP_2 -> "U2"
            UP_PRIME -> "U'"

            LEFT -> "L"
            LEFT_2 -> "L2"
            LEFT_PRIME -> "L'"

            FRONT -> "F"
            FRONT_2 -> "F2"
            FRONT_PRIME -> "F'"

            RIGHT -> "R"
            RIGHT_2 -> "R2"
            RIGHT_PRIME -> "R'"

            BACK -> "B"
            BACK_2 -> "B2"
            BACK_PRIME -> "B'"

            DOWN -> "D"
            DOWN_2 -> "D2"
            DOWN_PRIME -> "D'"
        }
    }

    fun moveCount(): Int {
        return when (this) {
            UP, LEFT, RIGHT, DOWN, BACK, FRONT -> 1
            UP_2, LEFT_2, RIGHT_2, DOWN_2, BACK_2, FRONT_2 -> 2
            UP_PRIME, LEFT_PRIME, RIGHT_PRIME, DOWN_PRIME, BACK_PRIME, FRONT_PRIME -> 3
        }
    }

    companion object {
        fun possibleNext(lastMove: Move): List<Move> {
            return when (lastMove) {
                DOWN, DOWN_2, DOWN_PRIME, UP, UP_2, UP_PRIME -> listOf(
                    LEFT,
                    LEFT_2,
                    LEFT_PRIME,
                    RIGHT,
                    RIGHT_2,
                    RIGHT_PRIME,
                    BACK,
                    BACK_2,
                    BACK_PRIME,
                    FRONT,
                    FRONT_2,
                    FRONT_PRIME
                )
                LEFT, LEFT_2, LEFT_PRIME, RIGHT, RIGHT_2, RIGHT_PRIME -> listOf(
                    DOWN,
                    DOWN_2,
                    DOWN_PRIME,
                    UP,
                    UP_2,
                    UP_PRIME,
                    BACK,
                    BACK_2,
                    BACK_PRIME,
                    FRONT,
                    FRONT_2,
                    FRONT_PRIME
                )
                FRONT, FRONT_2, FRONT_PRIME, BACK, BACK_2, BACK_PRIME -> listOf(
                    LEFT,
                    LEFT_2,
                    LEFT_PRIME,
                    RIGHT,
                    RIGHT_2,
                    RIGHT_PRIME,
                    DOWN,
                    DOWN_2,
                    DOWN_PRIME,
                    UP,
                    UP_2,
                    UP_PRIME
                )
            }
        }

        fun randomNext(lastMove: Move): Move {
            return possibleNext(lastMove).random()
        }
    }
}